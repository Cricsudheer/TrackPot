package com.example.trackpot.dto.match;

import com.example.trackpot.enums.MatchStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

/**
 * DTO for Match creation and update requests.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Match request data")
public class MatchRequestDto {
    
    @NotNull(message = "Player 1 ID is required")
    @Schema(description = "ID of the first player", example = "123e4567-e89b-12d3-a456-426614174000", required = true)
    private UUID player1Id;
    
    @NotNull(message = "Player 2 ID is required")
    @Schema(description = "ID of the second player", example = "123e4567-e89b-12d3-a456-426614174001", required = true)
    private UUID player2Id;
    
    @NotNull(message = "Best of frames is required")
    @Min(value = 1, message = "Best of frames must be at least 1")
    @Schema(description = "Number of frames to play (best of)", example = "7", required = true)
    private Integer bestOfFrames;
    
    @Schema(description = "Match start time", example = "2023-06-15T10:15:30Z")
    private Instant startTime;
    
    @Schema(description = "Match end time", example = "2023-06-15T12:30:45Z")
    private Instant endTime;
    
    @Schema(description = "Match status", example = "IN_PROGRESS", defaultValue = "NOT_STARTED")
    private MatchStatus status;
}