CREATE TABLE match
(
    id               UUID    NOT NULL,
    player_first_id  UUID,
    player_second_id UUID,
    best_of_frames   INTEGER NOT NULL,
    start_time       TIMESTAMP WITHOUT TIME ZONE,
    end_time         TIMESTAMP WITHOUT TIME ZONE,
    status           VARCHAR(255),
    CONSTRAINT pk_match PRIMARY KEY (id)
);

ALTER TABLE match
    ADD CONSTRAINT FK_MATCH_ON_PLAYER_FIRST FOREIGN KEY (player_first_id) REFERENCES player (id);

ALTER TABLE match
    ADD CONSTRAINT FK_MATCH_ON_PLAYER_SECOND FOREIGN KEY (player_second_id) REFERENCES player (id);