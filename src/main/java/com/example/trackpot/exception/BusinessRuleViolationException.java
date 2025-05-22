package com.example.trackpot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a business rule is violated.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessRuleViolationException extends RuntimeException {

    /**
     * Constructs a new BusinessRuleViolationException with the specified detail message.
     *
     * @param message the detail message
     */
    public BusinessRuleViolationException(String message) {
        super(message);
    }

    /**
     * Constructs a new BusinessRuleViolationException with the specified rule name and reason.
     *
     * @param ruleName the name of the business rule that was violated
     * @param reason the reason why the rule was violated
     */
    public BusinessRuleViolationException(String ruleName, String reason) {
        super(String.format("Business rule '%s' violated: %s", ruleName, reason));
    }
}