package io.truthencode.ddo.codex.model;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "RaceFamily.getByName", query = "from RaceFamily where name = ?1"),
})
public class RaceFamily extends PanacheEntity {

    public String name;


    public static RaceFamily getByName(String name) {
        var rVal = find("#RaceFamily.getByName", name).firstResult();
        return null;
    }
}
