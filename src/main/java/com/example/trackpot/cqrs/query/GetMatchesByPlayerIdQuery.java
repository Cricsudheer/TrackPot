package com.example.trackpot.cqrs.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Query for retrieving matches for a specific player.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMatchesByPlayerIdQuery {
    private UUID playerId;
}