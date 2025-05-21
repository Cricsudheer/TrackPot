package com.example.trackpot.dto.player;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Player creation and update requests.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Player request data")
public class PlayerRequestDto {
    
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    @Schema(description = "Player name", example = "John Smith", required = true)
    private String name;
    
    @Schema(description = "URL to player's avatar image", example = "https://example.com/avatar.jpg")
    private String avatarUrl;
}