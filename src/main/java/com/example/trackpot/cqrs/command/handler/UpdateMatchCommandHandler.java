package com.example.trackpot.cqrs.command.handler;

import com.example.trackpot.cqrs.command.UpdateMatchCommand;
import com.example.trackpot.models.SnookerMatch;
import com.example.trackpot.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Handler for the UpdateMatchCommand.
 */
@Component
@RequiredArgsConstructor
public class UpdateMatchCommandHandler {
    
    private final MatchService matchService;
    
    /**
     * Handles the UpdateMatchCommand by updating an existing match.
     *
     * @param command the command to handle
     * @return true if the update was successful
     */
    public boolean handle(UpdateMatchCommand command) {
        SnookerMatch match = SnookerMatch.builder()
                .bestOfFrames(command.getBestOfFrames())
                .startTime(command.getStartTime())
                .endTime(command.getEndTime())
                .status(command.getStatus())
                .build();
        
        matchService.updateMatch(command.getId(), match, command.getPlayer1Id(), command.getPlayer2Id());
        return true;
    }
}