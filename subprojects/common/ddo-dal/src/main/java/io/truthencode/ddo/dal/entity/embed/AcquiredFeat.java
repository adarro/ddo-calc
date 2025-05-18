package io.truthencode.ddo.dal.entity.embed;

import jakarta.persistence.Embeddable;

/**
 * Represents a feat that has been acquired by a character.
 * <p>
 * This embeddable class captures details about a specific feat, including its identifier,
 * the character level at which it was acquired, and its source category.
 */
@Embeddable
public class AcquiredFeat {
    /**
     * The id of the feat.
     */
    private String featId;

    /**
     * Gets the String key identifying the feat.
     *
     * @return  the String key identifying the feat.
     */
    public String getFeatId() {
        return featId;
    }

    /**
     * Sets the String key identifying the feat.
     *
     * @param featId the String key identifying the feat.
     */
    public void setFeatId(String featId) {
        this.featId = featId;
    }

    /**
     * Character Level at which the feat was acquired.
     */
    private int level;

    /**
     * Gets the character level at which the feat was acquired.
     *
     * @return the character level at which the feat was acquired.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the character level at which the feat was acquired.
     *
     * @param level the character level at which the feat was acquired.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    private EffectSource sourceCategory;

    /**
     * Gets the source category of the feat.
     * Examples include from a Item like a weapon, a spell, stance, or a class feature.
     *
     * @return the source category of the feat.
     */
    public EffectSource getSourceCategory() {
        return sourceCategory;
    }

    /**
     * Sets the source category of the feat.
     *
     * @param sourceCategory the source category of the feat to set.
     */
    public void setSourceCategory(EffectSource sourceCategory) {
        this.sourceCategory = sourceCategory;
    }


}
