package com.example.trackpot.cqrs.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Command to delete a match.
 */
@Data
@AllArgsConstructor
public class DeleteMatchCommand {
    
    /**
     * The ID of the match to delete.
     */
    private final UUID id;
}