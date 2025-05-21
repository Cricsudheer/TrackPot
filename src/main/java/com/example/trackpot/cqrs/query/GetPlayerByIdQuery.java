package com.example.trackpot.cqrs.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Query for retrieving a player by ID.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPlayerByIdQuery {
    private UUID id;
}