package com.example.trackpot.controller;

import com.example.trackpot.cqrs.command.CreatePlayerCommand;
import com.example.trackpot.cqrs.command.handler.CreatePlayerCommandHandler;
import com.example.trackpot.cqrs.query.GetPlayerByIdQuery;
import com.example.trackpot.cqrs.query.handler.GetPlayerByIdQueryHandler;
import com.example.trackpot.dto.player.PlayerRequestDto;
import com.example.trackpot.dto.player.PlayerResponseDto;
import com.example.trackpot.exception.ResourceNotFoundException;
import com.example.trackpot.mapper.PlayerMapper;
import com.example.trackpot.models.Player;
import com.example.trackpot.service.PlayerService;
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
 * REST controller for managing Player resources.
 */
@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
@Tag(name = "Player", description = "Player management API")
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerMapper playerMapper;
    private final CreatePlayerCommandHandler createPlayerCommandHandler;
    private final GetPlayerByIdQueryHandler getPlayerByIdQueryHandler;

    /**
     * GET /api/players : Get all players
     *
     * @return the ResponseEntity with status 200 (OK) and the list of players in the body
     */
    @GetMapping
    @Operation(summary = "Get all players", description = "Returns a list of all players")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved players")
    public ResponseEntity<List<PlayerResponseDto>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return ResponseEntity.ok(playerMapper.toDtoList(players));
    }

    /**
     * GET /api/players/{id} : Get a player by ID
     *
     * @param id the ID of the player to retrieve
     * @return the ResponseEntity with status 200 (OK) and the player in the body, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a player by ID", description = "Returns a player as per the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved player"),
            @ApiResponse(responseCode = "404", description = "Player not found", 
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<PlayerResponseDto> getPlayerById(
            @Parameter(description = "ID of the player to retrieve", required = true)
            @PathVariable UUID id) {
        // Create a query
        GetPlayerByIdQuery query = new GetPlayerByIdQuery(id);

        // Handle the query
        PlayerResponseDto playerDto = getPlayerByIdQueryHandler.handle(query);

        return ResponseEntity.ok(playerDto);
    }

    /**
     * POST /api/players : Create a new player
     *
     * @param playerRequestDto the player to create
     * @return the ResponseEntity with status 201 (Created) and the new player in the body
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new player", description = "Creates a new player and returns the created player")
    @ApiResponse(responseCode = "201", description = "Player successfully created")
    public ResponseEntity<PlayerResponseDto> createPlayer(
            @Parameter(description = "Player to create", required = true)
            @Valid @RequestBody PlayerRequestDto playerRequestDto) {
        // Create a command from the request DTO
        CreatePlayerCommand command = CreatePlayerCommand.builder()
                .name(playerRequestDto.getName())
                .avatarUrl(playerRequestDto.getAvatarUrl())
                .build();

        // Handle the command
        UUID playerId = createPlayerCommandHandler.handle(command);

        // Retrieve the created player
        Player player = playerService.getPlayerById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player", playerId));

        return ResponseEntity.status(HttpStatus.CREATED).body(playerMapper.toDto(player));
    }

    /**
     * PUT /api/players/{id} : Update an existing player
     *
     * @param id the ID of the player to update
     * @param playerRequestDto the player to update
     * @return the ResponseEntity with status 200 (OK) and the updated player in the body, or with status 404 (Not Found)
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a player", description = "Updates a player and returns the updated player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player successfully updated"),
            @ApiResponse(responseCode = "404", description = "Player not found", 
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<PlayerResponseDto> updatePlayer(
            @Parameter(description = "ID of the player to update", required = true)
            @PathVariable UUID id,
            @Parameter(description = "Updated player details", required = true)
            @Valid @RequestBody PlayerRequestDto playerRequestDto) {
        Player player = playerMapper.toEntity(playerRequestDto);
        Player updatedPlayer = playerService.updatePlayer(id, player);
        return ResponseEntity.ok(playerMapper.toDto(updatedPlayer));
    }

    /**
     * DELETE /api/players/{id} : Delete a player
     *
     * @param id the ID of the player to delete
     * @return the ResponseEntity with status 204 (NO_CONTENT), or with status 404 (Not Found)
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a player", description = "Deletes a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Player successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Player not found", 
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<Void> deletePlayer(
            @Parameter(description = "ID of the player to delete", required = true)
            @PathVariable UUID id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}
