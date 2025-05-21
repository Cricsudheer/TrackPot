package com.example.trackpot.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import java.time.Instant;

@Entity
@Table(
        name = "score_event",
        indexes = @Index(name = "idx_event_frame_ts", columnList = "frame_id, timestamp")
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "event_type", discriminatorType = DiscriminatorType.STRING)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public abstract class ScoreEvent {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false) @JoinColumn(name = "frame_id", nullable = false)
    private Frame frame;

    @ManyToOne(optional = false) @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(nullable = false)
    private Instant timestamp;
}
