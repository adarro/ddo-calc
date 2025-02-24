package io.truthencode.ddo.codex.model.xref;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class FeatData extends PanacheEntity {
    @NotNull
    public String name;
    @NotBlank
    public String description;
}
