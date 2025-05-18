package io.truthencode.ddo.dal.entity;

import io.truthencode.ddo.dal.entity.audit.Audit;
import io.truthencode.ddo.dal.entity.audit.AuditListener;
import io.truthencode.ddo.dal.entity.audit.Auditable;
import jakarta.persistence.*;
//import org.jetbrains.annotations.NotNull;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a category entity in the application's data model. Categories are used to organize and
 * classify various entities within the system.
 */
@Entity
@EntityListeners(AuditListener.class)
public class Category implements Auditable {
    @Transient
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /** The unique identifier for the category. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Audit audit;

    /** The name of the category. */
    @Column(unique = true)
    private String name;

    /**
     * The display text associated with the category.
     */
    @Column(name = "display_name")
    private String displayText;

    /**
     * Gets the unique identifier for the category.
     * @return the unique identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the category.
     * @param id the unique identifier to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the category.
     * @return the name of the category
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the display text associated with the category.
     * @return the display text
     */
    public String getDisplayText() {
        return displayText;
    }

    /**
     * Sets the display text associated with the category.
     * @param displayText   the display text to set
     */
    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    /**
     * Gets the audit information for the category.
     * @return the audit information
     */
    @Override
    public Audit findAudit() {
        logger.warn("Looking for audit");
        if (audit == null) {
            logger.error("audit was not initialized");
            audit = new Audit();
        }
        return audit;
    }

    /**
     * Sets the audit information for the category.
     * @param audit the audit information to set
     */
    @Override
    public void loadAudit( Audit audit) {
        this.audit = audit;
    }
}
