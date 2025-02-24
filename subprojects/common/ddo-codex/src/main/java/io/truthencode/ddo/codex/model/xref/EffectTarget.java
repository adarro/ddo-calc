package io.truthencode.ddo.codex.model.xref;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class EffectTarget extends PanacheEntity {
    @NotNull
    public String name;
    public String description;
    @OneToMany
    public List<CategoryData> categories;

}
