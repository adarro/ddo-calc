package io.truthencode.dal.general;


/**
 * Represents the different types of usage for a feat or ability.
 * Defines whether a feat's effects are always active or require a specific trigger.
 */
public enum Usage {
    /**
     * Feat has effects that are always active without the need for a trigger.
     */
    PASSIVE,
    /**
     * Feat has effects that are triggered by a specific event or action.
     */
    ACTIVE
}
