package com.example.trackpot.cqrs.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Command to delete a player.
 */
@Data
@AllArgsConstructor
public class DeletePlayerCommand {
    
    /**
     * The ID of the player to delete.
     */
    private final UUID id;
}