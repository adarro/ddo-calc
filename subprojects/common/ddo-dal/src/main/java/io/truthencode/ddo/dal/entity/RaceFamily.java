package io.truthencode.ddo.dal.entity;

import jakarta.persistence.*;

/**
 * Represents a race family in the game, mapping to the 'race_family' database table.
 *
 * This entity class defines the structure for storing race family information,
 * including a unique identifier and name.
 */
@Entity
@Table(name = "race_family")
public class RaceFamily {
    /**
         * Default constructor required by JPA for entity instantiation.
         * This no-argument constructor allows JPA providers to create instances of the RaceFamily entity.
         */
    public RaceFamily() {
        // JPA Required Default constructor
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * Gets the unique identifier for the race family.
     * @return  the unique identifier for the race family.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the race family.
     * @param id  the unique identifier for the race family.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the race family.
     * @return the name of the race family.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the race family.
     *
     * @param name The name to be assigned to this race family.
     */
    public void setName(String name) {
        this.name = name;
    }
}
