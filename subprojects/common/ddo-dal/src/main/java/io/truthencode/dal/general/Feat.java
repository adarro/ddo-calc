package io.truthencode.dal.general;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Cacheable
public class Feat extends PanacheEntity {
    @Column(unique = true)
    @NotBlank
    public String name;

    public String description;

    @Size(min=1,max=2,message="Must have at least one usage and at most two usages")
    public Set<Usage> usages;

    public Feat() {
    }

    public Feat(String name) {
        this.name = name;
    }


    public Feat(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Feat(String name, String description, Set<Usage> usages) {
        this.name = name;
        this.description = description;
        this.usages = usages;
    }
}
