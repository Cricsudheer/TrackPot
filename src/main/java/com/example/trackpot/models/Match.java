package com.example.trackpot.models;

import com.example.trackpot.enums.MatchStatus;
import jakarta.persistence.*;
import org.springframework.beans.factory.config.YamlProcessor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "match")
public class Match {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "player_first_id")
    private Player player1;

    @ManyToOne @JoinColumn(name = "player_second_id")
    private Player player2;

    private int bestOfFrames;
    private Instant startTime;
    private Instant endTime;

    @Enumerated(EnumType.STRING)
    private MatchStatus status;

}
