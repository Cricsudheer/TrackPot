package com.example.trackpot.cqrs.query.handler;

import com.example.trackpot.cqrs.query.GetAllMatchesQuery;
import com.example.trackpot.dto.match.MatchResponseDto;
import com.example.trackpot.mapper.MatchMapper;
import com.example.trackpot.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Handler for the GetAllMatchesQuery.
 */
@Component
@RequiredArgsConstructor
public class GetAllMatchesQueryHandler {
    
    private final MatchService matchService;
    private final MatchMapper matchMapper;
    
    /**
     * Handles the GetAllMatchesQuery by retrieving all matches.
     *
     * @param query the query to handle
     * @return a list of all matches as MatchResponseDtos
     */
    public List<MatchResponseDto> handle(GetAllMatchesQuery query) {
        return matchMapper.toDtoList(matchService.getAllMatches());
    }
}