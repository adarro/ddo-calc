package io.truthencode.dal.general;

import jakarta.validation.groups.Default;

/**
 * Marker interfaces for validation groups.
 */
public interface ValidationGroups {
    /**
     * Validation group for creating new entities.
     */
    interface Post extends Default {
    }

    /**
     * Validation group for updating existing entities.
     */
    interface Put extends Default {
    }
}
