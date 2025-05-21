package com.example.trackpot.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreEventDto {
    private UUID frameId;
    private UUID playerId;
    private int points;
    private Instant timestamp;
}
