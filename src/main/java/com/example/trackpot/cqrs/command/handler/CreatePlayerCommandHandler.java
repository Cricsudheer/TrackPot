package com.example.trackpot.cqrs.command.handler;

import com.example.trackpot.cqrs.command.CreatePlayerCommand;
import com.example.trackpot.models.Player;
import com.example.trackpot.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Handler for the CreatePlayerCommand.
 */
@Component
@RequiredArgsConstructor
public class CreatePlayerCommandHandler {
    
    private final PlayerService playerService;
    
    /**
     * Handles the CreatePlayerCommand by creating a new player.
     *
     * @param command the command to handle
     * @return the ID of the created player
     */
    public UUID handle(CreatePlayerCommand command) {
        Player player = Player.builder()
                .name(command.getName())
                .avatarUrl(command.getAvatarUrl())
                .build();
        
        Player createdPlayer = playerService.createPlayer(player);
        return createdPlayer.getId();
    }
}