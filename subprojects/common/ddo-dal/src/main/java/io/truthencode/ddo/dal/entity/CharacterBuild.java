package io.truthencode.ddo.dal.entity;

import io.truthencode.ddo.dal.entity.audit.Audit;
import io.truthencode.ddo.dal.entity.audit.AuditListener;
import io.truthencode.ddo.dal.entity.audit.Auditable;
import jakarta.persistence.*;

/**
 * Represents a character build in the game, tracking its unique identifier, associated world, and audit information.
 *
 * This class is a JPA entity that supports auditing and is associated with a specific game world.
 * It provides methods to manage its identifier, world, and audit trail.
 */
@Entity
@EntityListeners(AuditListener.class)
public class CharacterBuild implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Audit audit;

    @ManyToOne
    @JoinColumn(name = "world_id")
    private World world;

    /**
     * Gets the unique identifier for the character build.
     * @return the unique identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the character build.
     * @param id the unique identifier to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the associated world for the character build.
     * @return the associated world
     */
    public World getWorld() {
        return world;
    }

    /**
     * Sets the associated world for the character build.
     * @param world the associated world to set
     */
    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Gets the audit information.
     *
     * @return the audit information.
     */
    @Override
    public Audit findAudit() {
        if (audit == null) {
            loadAudit(new Audit());
        }
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
