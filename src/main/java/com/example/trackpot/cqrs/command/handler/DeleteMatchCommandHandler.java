package com.example.trackpot.cqrs.command.handler;

import com.example.trackpot.cqrs.command.DeleteMatchCommand;
import com.example.trackpot.exception.ResourceNotFoundException;
import com.example.trackpot.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Handler for {@link DeleteMatchCommand}.
 * Deletes a match from the database.
 */
@Component
@RequiredArgsConstructor
public class DeleteMatchCommandHandler {

    private final MatchService matchService;

    /**
     * Handles the command to delete a match.
     *
     * @param command the command
     * @throws ResourceNotFoundException if the match is not found
     */
    public void handle(DeleteMatchCommand command) {
        // Check if match exists
        matchService.getMatchById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Match", command.getId()));
        
        // Delete the match
        matchService.deleteMatch(command.getId());
    }
}