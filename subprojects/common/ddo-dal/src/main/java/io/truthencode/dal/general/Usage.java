package io.truthencode.dal.general;

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
