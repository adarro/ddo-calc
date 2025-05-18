package io.truthencode.ddo.dal.entity.embed;

/**
 * The categorical source of the effect.
 */
public enum EffectSource {
    /**
     * The effect originates from an item.
     * This is generally a piece of equipment.
     */
    Item,
    /**
     * The effect originates from an ability.
     */
    Ability,
    /**
     * The effect is granted by a feat.
     */
    Feat,
    /**
     * The effect originates from a class based ability.
     */
    Class,
    /**
     * The effect originates from a usually innate race based ability.
     */
    Race,
    /**
     * The effect originates from a class feature.
     * I'm not sure what this is.
     */
    ClassFeature,
    /**
     * The effect originates from a background.
     */
    Background,
    /**
     * The effect originates from a Destiny Enhancement
     */
    Destiny,
    /**
     * The effect originates from a prestige Enhancement such as Arcane Archer or Falconer.
     */
    Enhancement
}
