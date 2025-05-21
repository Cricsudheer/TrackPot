package com.example.trackpot.cqrs.query.handler;

import com.example.trackpot.cqrs.query.GetMatchesByPlayerIdQuery;
import com.example.trackpot.dto.match.MatchResponseDto;
import com.example.trackpot.mapper.MatchMapper;
import com.example.trackpot.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Handler for the GetMatchesByPlayerIdQuery.
 */
@Component
@RequiredArgsConstructor
public class GetMatchesByPlayerIdQueryHandler {
    
    private final MatchService matchService;
    private final MatchMapper matchMapper;
    
    /**
     * Handles the GetMatchesByPlayerIdQuery by retrieving matches for a specific player.
     *
     * @param query the query to handle
     * @return a list of matches for the player as MatchResponseDtos
     */
    public List<MatchResponseDto> handle(GetMatchesByPlayerIdQuery query) {
        return matchMapper.toDtoList(matchService.getMatchesByPlayerId(query.getPlayerId()));
    }
}