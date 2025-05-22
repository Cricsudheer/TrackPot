package com.example.trackpot.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.UUID;

/**
 * Base DTO for score event entities.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class ScoreEventBaseDto {
    private UUID id;
    private UUID frameId;
    private UUID playerId;
    private String playerName;
    private Instant timestamp;
    private String eventType;
}