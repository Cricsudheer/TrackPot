package com.example.trackpot.models;


import com.example.trackpot.enums.MatchStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "snooker_match",
        indexes = {
                @Index(name = "idx_match_player1", columnList = "player1_id"),
                @Index(name = "idx_match_player2", columnList = "player2_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SnookerMatch {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false) @JoinColumn(name = "player1_id", nullable = false)
    private Player player1;

    @ManyToOne(optional = false) @JoinColumn(name = "player2_id", nullable = false)
    private Player player2;

    @Column(name = "best_of_frames", nullable = false)
    private int bestOfFrames;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchStatus status;

    @OneToMany(
            mappedBy = "match",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("frameIndex ASC")
    private List<Frame> frames = new ArrayList<>();
}