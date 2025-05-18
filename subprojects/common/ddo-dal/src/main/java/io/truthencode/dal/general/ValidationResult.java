package io.truthencode.dal.general;

import jakarta.validation.ConstraintViolation;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents the result of a validation process.
 *
 * Indicates whether a validation was successful and provides a message
 * describing the validation outcome or any constraint violations.
 */
public class ValidationResult {

    /**
     * Creates a successful validation result with the specified message.
     *
     * @param message The success message
     */
    ValidationResult(String message) {
        this.success = true;
        this.message = message;
    }

    /**
     * Creates a failed validation result from a set of constraint violations.
     * The message is constructed by joining all violation messages.
     *
     * @param violations Set of constraint violations that caused validation to fail
     */
    ValidationResult(Set<? extends ConstraintViolation<?>> violations) {
        this.success = false;
        this.message = violations.stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.joining(", "));
    }

    private final String message;
    private final boolean success;

    /**
     * Returns the validation message.
     *
     * @return A success message or a concatenated list of validation error messages
     */
    public String getMessage() {
        return message;
    }

    /**
     * Indicates whether validation was successful.
     *
     * @return true if validation succeeded, false otherwise
     */
    public boolean isSuccess() {
        return success;
    }


}
