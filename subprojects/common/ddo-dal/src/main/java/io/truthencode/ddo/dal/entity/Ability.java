package io.truthencode.ddo.dal.entity;

import io.truthencode.ddo.dal.entity.audit.Audit;
import io.truthencode.ddo.dal.entity.audit.AuditListener;
import io.truthencode.ddo.dal.entity.audit.Auditable;
import jakarta.persistence.*;

/**
 * Represents an Ability entity in the game system.
 *
 * This class defines the core attributes and audit capabilities for game abilities,
 * implementing the Auditable interface to track creation, modification, and other
 * audit-related information.
 *
 * @see Auditable
 * @see Audit
 */
@Entity
@EntityListeners(AuditListener.class)
public class Ability implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the world
     */
    private String name;

    /** The audit information for the effect. */
    @Embedded
    private Audit audit;

    /**
     * Gets the ID of the ability.
     * @return the ID of the ability.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the ability.
     * @param id the ID of the ability to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the ability.
     * @return the name of the ability.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the ability.
     * @param name the name of the ability to set.
     */
    public void setName(String name) {
        this.name = name;
    }

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
     * @param audit the audit information to set.
     */
    @Override
    public void loadAudit(Audit audit) {
        this.audit = audit;
    }
}
