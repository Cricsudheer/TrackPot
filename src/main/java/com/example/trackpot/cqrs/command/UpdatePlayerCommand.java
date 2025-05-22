package com.example.trackpot.cqrs.command;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Command to update an existing player.
 */
@Data
@Builder
public class UpdatePlayerCommand {
    
    /**
     * The ID of the player to update.
     */
    private final UUID id;
    
    /**
     * The new name of the player.
     */
    private final String name;
    
    /**
     * The new avatar URL of the player.
     */
    private final String avatarUrl;
}