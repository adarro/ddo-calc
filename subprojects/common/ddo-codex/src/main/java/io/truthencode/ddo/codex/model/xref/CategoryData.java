package io.truthencode.ddo.codex.model.xref;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

/**
 * Represents a category in the DDO Codex. Categories can have a parent category and child categories.
 * The `isRoot()` method can be used to check if a category is a top-level (root) category.
 * The `hasChildren()` method can be used to check if a category has any child categories.
 * The `findByName()` method can be used to retrieve a category by its name.
 */
@Entity
public class CategoryData extends PanacheEntity {
    private static final Logger log = LoggerFactory.getLogger(CategoryData.class);
    @NotNull
    public String name;
    public String description;

    /**
     * The parent category of this category.
     * You can use isRoot() to check if the category is a root category.
     */
    @ManyToOne
    public CategoryData parent;

    /**
     * The child categories of this category.
     */
    @OneToMany(mappedBy = "parent")
    public Collection<CategoryData> children;

    /**
     * Checks if the current category is a top-level (root) category.
     * @return true if the category is a root category, false otherwise.
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * Checks if the current category has any child categories.
     *
     * @return true if the category has one or more child categories, false otherwise.
     */
    public boolean hasChild() {
        return children != null && !children.isEmpty();
    }

    /**
     * Finds a category by its name.
     * @param name name of the category to find
     * @return the category with the given name, or null if not found
     */
    public static CategoryData findByName(@NotBlank String name) {
        return find("name", name).firstResult();
    }

    /**
     * Finds a category by name using like
     * @param search string to search for
     * @return a list of categories that match the search string
     */
    public static List<CategoryData> search(@NotBlank String search) {
        log.info("search for {}", search);
        List<CategoryData> cat = CategoryData.find("name like ?1", "%" + search + "%").list();
        if (log.isInfoEnabled()) {
            log.info("search for {} found {}", search, cat.size());
        }
        return cat;
    }
}
