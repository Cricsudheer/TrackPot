package com.example.trackpot.controller;

import com.example.trackpot.cqrs.command.CreateMatchCommand;
import com.example.trackpot.cqrs.command.UpdateMatchCommand;
import com.example.trackpot.cqrs.command.handler.CreateMatchCommandHandler;
import com.example.trackpot.cqrs.command.handler.UpdateMatchCommandHandler;
import com.example.trackpot.cqrs.query.GetAllMatchesQuery;
import com.example.trackpot.cqrs.query.GetMatchByIdQuery;
import com.example.trackpot.cqrs.query.GetMatchesByPlayerIdQuery;
import com.example.trackpot.cqrs.query.handler.GetAllMatchesQueryHandler;
import com.example.trackpot.cqrs.query.handler.GetMatchByIdQueryHandler;
import com.example.trackpot.cqrs.query.handler.GetMatchesByPlayerIdQueryHandler;
import com.example.trackpot.dto.match.MatchRequestDto;
import com.example.trackpot.dto.match.MatchResponseDto;
import com.example.trackpot.exception.ResourceNotFoundException;
import com.example.trackpot.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing Match resources.
 */
@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
@Tag(name = "Match", description = "Match management API")
public class MatchController {

    private final MatchService matchService;
    private final CreateMatchCommandHandler createMatchCommandHandler;
    private final UpdateMatchCommandHandler updateMatchCommandHandler;
    private final GetMatchByIdQueryHandler getMatchByIdQueryHandler;
    private final GetAllMatchesQueryHandler getAllMatchesQueryHandler;
    private final GetMatchesByPlayerIdQueryHandler getMatchesByPlayerIdQueryHandler;

    /**
     * GET /api/matches : Get all matches
     *
     * @return the ResponseEntity with status 200 (OK) and the list of matches in the body
     */
    @GetMapping
    @Operation(summary = "Get all matches", description = "Returns a list of all matches")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved matches")
    public ResponseEntity<List<MatchResponseDto>> getAllMatches() {
        GetAllMatchesQuery query = new GetAllMatchesQuery();
        List<MatchResponseDto> matches = getAllMatchesQueryHandler.handle(query);
        return ResponseEntity.ok(matches);
    }

    /**
     * GET /api/matches/{id} : Get a match by ID
     *
     * @param id the ID of the match to retrieve
     * @return the ResponseEntity with status 200 (OK) and the match in the body, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a match by ID", description = "Returns a match as per the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved match"),
            @ApiResponse(responseCode = "404", description = "Match not found", 
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<MatchResponseDto> getMatchById(
            @Parameter(description = "ID of the match to retrieve", required = true)
            @PathVariable UUID id) {
        GetMatchByIdQuery query = new GetMatchByIdQuery(id);
        MatchResponseDto match = getMatchByIdQueryHandler.handle(query);
        return ResponseEntity.ok(match);
    }

    /**
     * GET /api/matches/player/{playerId} : Get all matches for a player
     *
     * @param playerId the ID of the player
     * @return the ResponseEntity with status 200 (OK) and the list of matches in the body
     */
    @GetMapping("/player/{playerId}")
    @Operation(summary = "Get all matches for a player", description = "Returns a list of matches for a specific player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved matches"),
            @ApiResponse(responseCode = "404", description = "Player not found", 
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<List<MatchResponseDto>> getMatchesByPlayerId(
            @Parameter(description = "ID of the player", required = true)
            @PathVariable UUID playerId) {
        GetMatchesByPlayerIdQuery query = new GetMatchesByPlayerIdQuery(playerId);
        List<MatchResponseDto> matches = getMatchesByPlayerIdQueryHandler.handle(query);
        return ResponseEntity.ok(matches);
    }

    /**
     * POST /api/matches : Create a new match
     *
     * @param matchRequestDto the match to create
     * @return the ResponseEntity with status 201 (Created) and the new match in the body
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new match", description = "Creates a new match and returns the created match")
    @ApiResponse(responseCode = "201", description = "Match successfully created")
    public ResponseEntity<MatchResponseDto> createMatch(
            @Parameter(description = "Match to create", required = true)
            @Valid @RequestBody MatchRequestDto matchRequestDto) {
        // Create a command from the request DTO
        CreateMatchCommand command = CreateMatchCommand.builder()
                .player1Id(matchRequestDto.getPlayer1Id())
                .player2Id(matchRequestDto.getPlayer2Id())
                .bestOfFrames(matchRequestDto.getBestOfFrames())
                .startTime(matchRequestDto.getStartTime())
                .status(matchRequestDto.getStatus())
                .build();

        // Handle the command
        UUID matchId = createMatchCommandHandler.handle(command);

        // Retrieve the created match
        GetMatchByIdQuery query = new GetMatchByIdQuery(matchId);
        MatchResponseDto match = getMatchByIdQueryHandler.handle(query);

        return ResponseEntity.status(HttpStatus.CREATED).body(match);
    }

    /**
     * PUT /api/matches/{id} : Update an existing match
     *
     * @param id the ID of the match to update
     * @param matchRequestDto the match to update
     * @return the ResponseEntity with status 200 (OK) and the updated match in the body, or with status 404 (Not Found)
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a match", description = "Updates a match and returns the updated match")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Match successfully updated"),
            @ApiResponse(responseCode = "404", description = "Match not found", 
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<MatchResponseDto> updateMatch(
            @Parameter(description = "ID of the match to update", required = true)
            @PathVariable UUID id,
            @Parameter(description = "Updated match details", required = true)
            @Valid @RequestBody MatchRequestDto matchRequestDto) {
        // Create a command from the request DTO
        UpdateMatchCommand command = UpdateMatchCommand.builder()
                .id(id)
                .player1Id(matchRequestDto.getPlayer1Id())
                .player2Id(matchRequestDto.getPlayer2Id())
                .bestOfFrames(matchRequestDto.getBestOfFrames())
                .startTime(matchRequestDto.getStartTime())
                .endTime(matchRequestDto.getEndTime())
                .status(matchRequestDto.getStatus())
                .build();

        // Handle the command
        updateMatchCommandHandler.handle(command);

        // Retrieve the updated match
        GetMatchByIdQuery query = new GetMatchByIdQuery(id);
        MatchResponseDto match = getMatchByIdQueryHandler.handle(query);

        return ResponseEntity.ok(match);
    }

    /**
     * DELETE /api/matches/{id} : Delete a match
     *
     * @param id the ID of the match to delete
     * @return the ResponseEntity with status 204 (NO_CONTENT), or with status 404 (Not Found)
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a match", description = "Deletes a match")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Match successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Match not found", 
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<Void> deleteMatch(
            @Parameter(description = "ID of the match to delete", required = true)
            @PathVariable UUID id) {
        // Check if match exists
        matchService.getMatchById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match", id));
        
        // Delete the match
        matchService.deleteMatch(id);
        
        return ResponseEntity.noContent().build();
    }
}