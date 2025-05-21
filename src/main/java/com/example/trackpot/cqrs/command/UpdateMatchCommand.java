package com.example.trackpot.cqrs.command;

import com.example.trackpot.enums.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

/**
 * Command for updating an existing match.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMatchCommand {
    private UUID id;
    private UUID player1Id;
    private UUID player2Id;
    private int bestOfFrames;
    private Instant startTime;
    private Instant endTime;
    private MatchStatus status;
}