package io.truthencode.ddo.codex.model.xref;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class CharacterClass extends PanacheEntity {
    @NotNull
    public String name;

    public String description;
}
