package io.truthencode.dal.general;

import jakarta.validation.groups.Default;

/**
 * Marker interfaces for validation groups.
 */
public interface ValidationGroups {
    interface Post extends Default {
    }
    interface Put extends Default {
    }
}