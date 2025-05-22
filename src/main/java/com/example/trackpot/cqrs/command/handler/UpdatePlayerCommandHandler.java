package com.example.trackpot.cqrs.command.handler;

import com.example.trackpot.cqrs.command.UpdatePlayerCommand;
import com.example.trackpot.exception.ResourceNotFoundException;
import com.example.trackpot.models.Player;
import com.example.trackpot.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Handler for {@link UpdatePlayerCommand}.
 * Updates an existing player in the database.
 */
@Component
@RequiredArgsConstructor
public class UpdatePlayerCommandHandler {

    private final PlayerService playerService;

    /**
     * Handles the command to update a player.
     *
     * @param command the command
     * @return the ID of the updated player
     * @throws ResourceNotFoundException if the player is not found
     */
    public UUID handle(UpdatePlayerCommand command) {
        // Create a player entity with the updated values
        Player player = Player.builder()
                .name(command.getName())
                .avatarUrl(command.getAvatarUrl())
                .build();
        
        // Update the player in the database
        Player updatedPlayer = playerService.updatePlayer(command.getId(), player);
        
        return updatedPlayer.getId();
    }
}