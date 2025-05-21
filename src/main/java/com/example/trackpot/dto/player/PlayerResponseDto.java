package com.example.trackpot.dto.player;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO for Player responses.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Player response data")
public class PlayerResponseDto {
    
    @Schema(description = "Player unique identifier", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    
    @Schema(description = "Player name", example = "John Smith")
    private String name;
    
    @Schema(description = "URL to player's avatar image", example = "https://example.com/avatar.jpg")
    private String avatarUrl;
}