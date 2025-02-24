package io.truthencode.ddo.codex.model.xref;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import java.util.List;

@Entity
@NamedQueries({
    @NamedQuery(name = "RaceFamily.getByName", query = "from RaceFamily where name = ?1"),
})
public class RaceFamily extends PanacheEntity {

    @jakarta.validation.constraints.Size(min = 5, max = 20, message
        = "Family name must be between 10 and 200 characters")
    public String name;

    public String description;

    @OneToMany(mappedBy = "family")
    public List<Race> races;


    public static RaceFamily getByName(String name) {
        return find("#RaceFamily.getByName", name).firstResult();
    }
}