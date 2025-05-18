package io.truthencode.ddo.dal.entity;

import io.truthencode.dal.general.Usage;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Represents a Feat entity in the Dungeons &amp; Dragons Online (DDO) data access layer.
 * A Feat is a special ability or skill that a character can acquire to enhance their capabilities.
 */
@Entity
public class Feat {
    /**
     * The unique identifier for the effect.
     * -- GETTER --
     *  Gets the Internal ID of the feat
     * -- SETTER --
     *  Sets the Internal ID of the feat
     *
     */
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Default constructor for creating a Feat instance.
     * Required by JPA for object instantiation.
     */
    public Feat() {
        // Default constructor required by JPA
    }


    /**
     * -- GETTER --
     *  Gets the name of the feat.
     *
     *
     * -- SETTER --
     *  Sets the name of the feat.
     *
     */
    @Setter
    @Getter
    @Column(unique = true)
    @NotBlank
    private String name;

    /**
     * -- GETTER --
     *  Gets the description of the feat.
     *
     *
     * -- SETTER --
     *  Sets the description of the feat.
     *
     */
    @Setter
    @Getter
    private String description;

    /**
     * Gets the usages of the feat.
     * This determines whether this feat has active or passive effects.
     */
//    @Size(min = 1, max = 2, message = "Must have at least one usage and at most two usages")
    @Column
    @Enumerated(value = EnumType.STRING)
    @ElementCollection(targetClass = Usage.class, fetch = FetchType.EAGER)
    public Set<Usage> usages;

}
