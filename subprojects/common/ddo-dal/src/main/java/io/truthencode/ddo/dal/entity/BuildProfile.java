package io.truthencode.ddo.dal.entity;

import io.truthencode.ddo.dal.entity.audit.Audit;
import io.truthencode.ddo.dal.entity.audit.Auditable;
import io.truthencode.ddo.dal.entity.embed.AcquiredFeat;
import jakarta.persistence.*;

import java.util.List;

/**
 * Entity representing a character build profile in the DDO system.
 * Contains information about character build configuration including name, description,
 * acquired feats, and audit information.
 */
@Entity
public class BuildProfile implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    /**
     * A brief description of the build profile, it's intent and purpose.
     */
    private String description;

    @Embedded
    private Audit audit;


    /**
     * Gets the unique identifier for this build profile.
     *
     * @return The build profile's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this build profile.
     *
     * @param id The ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of this build profile.
     *
     * @return The build profile's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this build profile.
     *
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of this build profile.
     *
     * @return The build profile's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of this build profile.
     *
     * @param description The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @ElementCollection
    private List<AcquiredFeat> feats;

    /**
     * Gets the list of acquired feats for this build profile.
     *
     * @return The list of acquired feats
     */
    public List<AcquiredFeat> getFeats() {
        return feats;
    }

    /**
     * Sets the list of acquired feats for this build profile.
     *
     * @param feats The list of feats to set
     */
    public void setFeats(List<AcquiredFeat> feats) {
        this.feats = feats;
    }


    // Past-life feats


    /**
     * Gets the audit information.
     *
     * @return the audit information.
     */
    @Override
    public Audit findAudit() {
        return audit;
    }

    /**
     * Sets the audit information.
     *
     * @param audit the audit information to set
     */
    @Override
    public void loadAudit(Audit audit) {
        this.audit = audit;
    }
}
