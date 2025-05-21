package com.example.trackpot.cqrs.command.handler;

import com.example.trackpot.cqrs.command.CreateMatchCommand;
import com.example.trackpot.models.SnookerMatch;
import com.example.trackpot.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Handler for the CreateMatchCommand.
 */
@Component
@RequiredArgsConstructor
public class CreateMatchCommandHandler {
    
    private final MatchService matchService;
    
    /**
     * Handles the CreateMatchCommand by creating a new match.
     *
     * @param command the command to handle
     * @return the ID of the created match
     */
    public UUID handle(CreateMatchCommand command) {
        SnookerMatch match = SnookerMatch.builder()
                .bestOfFrames(command.getBestOfFrames())
                .startTime(command.getStartTime())
                .status(command.getStatus())
                .build();
        
        SnookerMatch createdMatch = matchService.createMatch(match, command.getPlayer1Id(), command.getPlayer2Id());
        return createdMatch.getId();
    }
}