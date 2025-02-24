package io.truthencode.ddo.codex.model.xref;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;



@Entity
public class Race extends PanacheEntity {
    @NotNull
    public String name;

    @ManyToOne
    public RaceFamily family;
}
