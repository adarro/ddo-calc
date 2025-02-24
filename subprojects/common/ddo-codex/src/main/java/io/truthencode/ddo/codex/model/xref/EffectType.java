package io.truthencode.ddo.codex.model.xref;

/**
 * The type of effect.
 * Used to determine if the effect modifies state (such as helpless or floating) or if it is a scaling effect (such as damage, healing, strength or hit-points).
 */
public enum EffectType {
    /**
     * Affects the value or scale of something
     */
    Scaling,
    /**
     * Affects the state of something
     */
    State
}
