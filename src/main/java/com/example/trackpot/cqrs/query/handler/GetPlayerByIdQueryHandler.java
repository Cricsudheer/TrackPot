package com.example.trackpot.cqrs.query.handler;

import com.example.trackpot.cqrs.query.GetPlayerByIdQuery;
import com.example.trackpot.dto.player.PlayerResponseDto;
import com.example.trackpot.exception.ResourceNotFoundException;
import com.example.trackpot.mapper.PlayerMapper;
import com.example.trackpot.models.Player;
import com.example.trackpot.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Handler for the GetPlayerByIdQuery.
 */
@Component
@RequiredArgsConstructor
public class GetPlayerByIdQueryHandler {
    
    private final PlayerService playerService;
    private final PlayerMapper playerMapper;
    
    /**
     * Handles the GetPlayerByIdQuery by retrieving a player by ID.
     *
     * @param query the query to handle
     * @return the player as a PlayerResponseDto
     * @throws ResourceNotFoundException if the player is not found
     */
    public PlayerResponseDto handle(GetPlayerByIdQuery query) {
        Player player = playerService.getPlayerById(query.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Player", query.getId()));
        
        return playerMapper.toDto(player);
    }
}