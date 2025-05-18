package io.truthencode.ddo.dal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

/**
 * Entity representing a playable race in the game.
 * Races can be standard or iconic and belong to specific race families.
 * Each race has a racial tier that determines its power level and availability.
 */
@Entity
public class Race {
    /**
         * Default constructor required by JPA for entity instantiation.
         * This no-argument constructor allows JPA providers to create instances of the Race entity.
         */
    public Race() {
        // JPA Required Default constructor
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ColumnDefault("false")
    private boolean iconic = false;

    @Enumerated(EnumType.STRING)
    private RacialTier tier;

    @ManyToOne
    @JoinColumn(name = "family_id")
    private RaceFamily raceFamily;

    /**
     * Returns the unique identifier of this race.
     *
     * @return The race's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this race.
     *
     * @param id The ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the name of this race.
     *
     * @return The race's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for this race.
     *
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Determines if this race is an iconic race.
     * Iconic races typically have special requirements or characteristics.
     *
     * @return true if this is an iconic race, false otherwise
     */
    public boolean isIconic() {
        return iconic;
    }

    /**
     * Sets whether this race is an iconic race.
     *
     * @param iconic true to mark as iconic, false otherwise
     */
    public void setIconic(boolean iconic) {
        this.iconic = iconic;
    }

    /**
     * Returns the racial tier of this race.
     *
     * @return The race's tier
     */
    public RacialTier getTier() {
        return tier;
    }

    /**
     * Sets the racial tier for this race.
     *
     * @param tier The racial tier to assign to this race
     */
    public void setTier(RacialTier tier) {
        this.tier = tier;
    }

    /**
     * Returns the race family this race belongs to.
     *
     * @return The race's family
     */
    public RaceFamily getRaceFamily() {
        return raceFamily;
    }

    /**
     * Sets the race family for this race.
     *
     * @param raceFamily The race family to assign to this race
     */
    public void setRaceFamily(RaceFamily raceFamily) {
        this.raceFamily = raceFamily;
    }
}
