package com.example.trackpot.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "frame",
        indexes = @Index(name = "idx_frame_match", columnList = "match_id")
)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Frame {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false) @JoinColumn(name = "match_id", nullable = false)
    private SnookerMatch match;

    /** “index” is a reserved word in Java, so we rename it: */
    @Column(name = "\"index\"", nullable = false)
    private int frameIndex;

    @ManyToOne @JoinColumn(name = "winner_id")
    private Player winner;

    @OneToMany(
            mappedBy = "frame",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("timestamp ASC")
    private List<ScoreEvent> events = new ArrayList<>();
}