-- 1. PLAYER
CREATE TABLE player (
                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                        name VARCHAR(255) NOT NULL,
                        avatar_url VARCHAR(512)
);

-- 2. match
CREATE TABLE snooker_match (
                               id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                               player1_id UUID NOT NULL,
                               player2_id UUID NOT NULL,
                               best_of_frames INT NOT NULL,
                               start_time TIMESTAMP WITH TIME ZONE NOT NULL,
                               end_time TIMESTAMP WITH TIME ZONE,
                               status VARCHAR(20) NOT NULL,
                               CONSTRAINT fk_match_p1 FOREIGN KEY (player1_id) REFERENCES player(id),
                               CONSTRAINT fk_match_p2 FOREIGN KEY (player2_id) REFERENCES player(id),
                               CONSTRAINT chk_match_status CHECK (status IN ('NOT_STARTED','IN_PROGRESS','COMPLETED'))
);

-- index to quickly look up matches by player
CREATE INDEX idx_match_player1 ON snooker_match(player1_id);
CREATE INDEX idx_match_player2 ON snooker_match(player2_id);

-- 3. FRAME
CREATE TABLE frame (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       match_id UUID NOT NULL,
                       index   INT  NOT NULL,
                       winner_id UUID,
                       CONSTRAINT fk_frame_match  FOREIGN KEY (match_id)  REFERENCES snooker_match(id),
                       CONSTRAINT fk_frame_winner FOREIGN KEY (winner_id) REFERENCES player(id)
);

-- index for fast frame lookup per match
CREATE INDEX idx_frame_match ON frame(match_id);

-- 4. SCORE_EVENT  (single‐table inheritance for PotEvent & FoulEvent)
CREATE TABLE score_event (
                             id          UUID      PRIMARY KEY DEFAULT gen_random_uuid(),
                             frame_id    UUID      NOT NULL,
                             player_id   UUID      NOT NULL,
                             timestamp   TIMESTAMPTZ NOT NULL,
                             event_type  VARCHAR(10) NOT NULL,   -- 'POT' or 'FOUL'
                             ball_type   VARCHAR(10),             -- for POT only
                             points      INT,                     -- for POT only
                             foul_points INT,                     -- for FOUL only
                             reason      TEXT,                    -- optional foul description
                             CONSTRAINT fk_event_frame  FOREIGN KEY (frame_id)  REFERENCES frame(id),
                             CONSTRAINT fk_event_player FOREIGN KEY (player_id) REFERENCES player(id),
                             CONSTRAINT chk_event_type   CHECK (event_type IN ('POT','FOUL')),
                             CONSTRAINT chk_ball_type    CHECK (ball_type IN ('RED','YELLOW','GREEN','BROWN','BLUE','PINK','BLACK') OR ball_type IS NULL)
);

-- index for ordering events by time within a frame
CREATE INDEX idx_event_frame_ts ON score_event(frame_id, timestamp);
