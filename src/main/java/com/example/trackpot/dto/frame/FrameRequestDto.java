package com.example.trackpot.dto.frame;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO for Frame creation and update requests.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Frame request data")
public class FrameRequestDto {

    @NotNull(message = "Match ID is required")
    @Schema(description = "ID of the match this frame belongs to", example = "123e4567-e89b-12d3-a456-426614174000", required = true)
    private UUID matchId;

    @Min(value = 1, message = "Frame index must be at least 1")
    @Schema(description = "Index of the frame within the match", example = "1", required = true)
    private int frameIndex;

    @Schema(description = "ID of the player who won the frame", example = "123e4567-e89b-12d3-a456-426614174001")
    private UUID winnerId;
}
