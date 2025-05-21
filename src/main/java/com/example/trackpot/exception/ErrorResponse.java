package com.example.trackpot.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Standard error response structure for API errors.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Standard error response")
public class ErrorResponse {
    
    @Schema(description = "HTTP status code", example = "404")
    private int status;
    
    @Schema(description = "Error message", example = "Resource not found")
    private String message;
    
    @Schema(description = "Request path", example = "uri=/api/players/123")
    private String path;
    
    @Schema(description = "Timestamp of the error", example = "2023-06-15T10:15:30")
    private LocalDateTime timestamp;
}