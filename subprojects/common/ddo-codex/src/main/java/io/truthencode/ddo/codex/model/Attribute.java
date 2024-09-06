package io.truthencode.ddo.codex.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

/**
 * Represents an Attribute of a Character, such as Strength, Dexterity, etc.
 */
@Entity
@Cacheable
public class Attribute extends PanacheEntity {
    /**
     * The name of the Attribute
     * This should be a valid Attribute name, such as Strength, Dexterity, etc.
     */
    @NotNull
    public String name;
}
