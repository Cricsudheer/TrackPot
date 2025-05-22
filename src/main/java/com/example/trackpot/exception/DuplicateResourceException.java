package com.example.trackpot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when attempting to create a resource that already exists.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateResourceException extends RuntimeException {

    /**
     * Constructs a new DuplicateResourceException with the specified detail message.
     *
     * @param message the detail message
     */
    public DuplicateResourceException(String message) {
        super(message);
    }

    /**
     * Constructs a new DuplicateResourceException with the specified resource type and identifier.
     *
     * @param resourceType the type of resource that already exists
     * @param identifier the identifier of the resource that already exists
     */
    public DuplicateResourceException(String resourceType, Object identifier) {
        super(String.format("%s with identifier %s already exists", resourceType, identifier));
    }
}