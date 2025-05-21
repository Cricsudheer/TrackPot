package com.example.trackpot.dto.match;

import com.example.trackpot.dto.player.PlayerResponseDto;
import com.example.trackpot.enums.MatchStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * DTO for Match responses.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Match response data")
public class MatchResponseDto {
    
    @Schema(description = "Match unique identifier", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    
    @Schema(description = "First player information")
    private PlayerResponseDto player1;
    
    @Schema(description = "Second player information")
    private PlayerResponseDto player2;
    
    @Schema(description = "Number of frames to play (best of)", example = "7")
    private int bestOfFrames;
    
    @Schema(description = "Match start time", example = "2023-06-15T10:15:30Z")
    private Instant startTime;
    
    @Schema(description = "Match end time", example = "2023-06-15T12:30:45Z")
    private Instant endTime;
    
    @Schema(description = "Match status", example = "IN_PROGRESS")
    private MatchStatus status;
    
    @Schema(description = "Current score for player 1", example = "3")
    private int player1Score;
    
    @Schema(description = "Current score for player 2", example = "2")
    private int player2Score;
    
    @Schema(description = "List of frame IDs in this match")
    private List<UUID> frameIds;
}