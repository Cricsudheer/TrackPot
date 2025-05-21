package com.example.trackpot.cqrs.query.handler;

import com.example.trackpot.cqrs.query.GetMatchByIdQuery;
import com.example.trackpot.dto.match.MatchResponseDto;
import com.example.trackpot.exception.ResourceNotFoundException;
import com.example.trackpot.mapper.MatchMapper;
import com.example.trackpot.models.SnookerMatch;
import com.example.trackpot.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Handler for the GetMatchByIdQuery.
 */
@Component
@RequiredArgsConstructor
public class GetMatchByIdQueryHandler {
    
    private final MatchService matchService;
    private final MatchMapper matchMapper;
    
    /**
     * Handles the GetMatchByIdQuery by retrieving a match by ID.
     *
     * @param query the query to handle
     * @return the match as a MatchResponseDto
     * @throws ResourceNotFoundException if the match is not found
     */
    public MatchResponseDto handle(GetMatchByIdQuery query) {
        SnookerMatch match = matchService.getMatchById(query.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Match", query.getId()));
        
        return matchMapper.toDto(match);
    }
}