package com.example.trackpot.cqrs.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Query for retrieving a match by ID.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMatchByIdQuery {
    private UUID id;
}