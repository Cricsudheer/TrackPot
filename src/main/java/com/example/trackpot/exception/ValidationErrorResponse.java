package com.example.trackpot.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Error response structure for validation errors.
 * Extends the standard ErrorResponse and adds a field for validation errors.
 */
@Getter
@Setter
@Schema(description = "Validation error response")
public class ValidationErrorResponse extends ErrorResponse {
    
    @Schema(description = "Map of field names to error messages", 
            example = "{\"name\":\"Name is required\",\"email\":\"Invalid email format\"}")
    private Map<String, String> errors;
    
    /**
     * Constructs a new ValidationErrorResponse with the specified details.
     *
     * @param status the HTTP status code
     * @param message the error message
     * @param path the request path
     * @param timestamp the timestamp of the error
     * @param errors the validation errors
     */
    public ValidationErrorResponse(int status, String message, String path, 
                                  LocalDateTime timestamp, Map<String, String> errors) {
        super(status, message, path, timestamp);
        this.errors = errors;
    }
}