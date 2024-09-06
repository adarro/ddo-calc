package io.truthencode.ddo.codex.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.validation.constraints.NotNull;

public abstract class Item extends PanacheEntity {
    @NotNull
    public String name;


    public String description;
}
