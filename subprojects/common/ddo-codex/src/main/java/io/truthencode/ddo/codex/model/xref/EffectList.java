package io.truthencode.ddo.codex.model.xref;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

/**
 * A list of known effects
 */
@Entity
public class EffectList extends PanacheEntity {
    @NotNull
    public String name;
    public String description;
    public EffectType type;
}
