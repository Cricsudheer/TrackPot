package com.example.trackpot.cqrs.command.handler;

import com.example.trackpot.cqrs.command.DeletePlayerCommand;
import com.example.trackpot.exception.ResourceNotFoundException;
import com.example.trackpot.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Handler for {@link DeletePlayerCommand}.
 * Deletes a player from the database.
 */
@Component
@RequiredArgsConstructor
public class DeletePlayerCommandHandler {

    private final PlayerService playerService;

    /**
     * Handles the command to delete a player.
     *
     * @param command the command
     * @throws ResourceNotFoundException if the player is not found
     */
    public void handle(DeletePlayerCommand command) {
        // Check if player exists
        playerService.getPlayerById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Player", command.getId()));
        
        // Delete the player
        playerService.deletePlayer(command.getId());
    }
}