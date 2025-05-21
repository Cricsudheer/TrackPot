package com.example.trackpot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested resource is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new ResourceNotFoundException with the specified resource type and identifier.
     *
     * @param resourceType the type of resource that was not found
     * @param resourceId the identifier of the resource that was not found
     */
    public ResourceNotFoundException(String resourceType, Object resourceId) {
        super(String.format("%s with id %s not found", resourceType, resourceId));
    }
}