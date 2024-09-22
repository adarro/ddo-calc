package io.truthencode.ddo.dal.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
//import io.quarkus.hibernate.reactive.panache.PanacheEntity;
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
