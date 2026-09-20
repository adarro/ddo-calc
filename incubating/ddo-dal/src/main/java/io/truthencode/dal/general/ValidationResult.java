package io.truthencode.dal.general;

import jakarta.validation.ConstraintViolation;

import java.util.Set;
import java.util.stream.Collectors;

public class ValidationResult {

        ValidationResult(String message) {
            this.success = true;
            this.message = message;
        }

        ValidationResult(Set<? extends ConstraintViolation<?>> violations) {
            this.success = false;
            this.message = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        }

        private final String message;
        private final boolean success;

        public String getMessage() {
            return message;
        }

        public boolean isSuccess() {
            return success;
        }


}
