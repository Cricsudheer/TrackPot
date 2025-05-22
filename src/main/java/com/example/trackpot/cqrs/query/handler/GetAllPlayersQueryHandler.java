package com.example.trackpot.cqrs.query.handler;

import com.example.trackpot.cqrs.query.GetAllPlayersQuery;
import com.example.trackpot.dto.player.PlayerResponseDto;
import com.example.trackpot.mapper.PlayerMapper;
import com.example.trackpot.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Handler for {@link GetAllPlayersQuery}.
 * Retrieves all players from the database.
 */
@Component
@RequiredArgsConstructor
public class GetAllPlayersQueryHandler {

    private final PlayerService playerService;
    private final PlayerMapper playerMapper;

    /**
     * Handles the query to retrieve all players.
     *
     * @param query the query
     * @return a list of all players as DTOs
     */
    public List<PlayerResponseDto> handle(GetAllPlayersQuery query) {
        return playerMapper.toDtoList(playerService.getAllPlayers());
    }
}