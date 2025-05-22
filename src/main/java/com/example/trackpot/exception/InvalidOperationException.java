package com.example.trackpot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an operation is not allowed due to the current state of a resource.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOperationException extends RuntimeException {

    /**
     * Constructs a new InvalidOperationException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidOperationException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidOperationException with the specified resource type, identifier, and reason.
     *
     * @param resourceType the type of resource
     * @param resourceId the identifier of the resource
     * @param reason the reason why the operation is not allowed
     */
    public InvalidOperationException(String resourceType, Object resourceId, String reason) {
        super(String.format("Cannot perform operation on %s with id %s: %s", resourceType, resourceId, reason));
    }
}