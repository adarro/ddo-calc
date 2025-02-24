package io.truthencode.ddo.codex.rest;

import io.quarkiverse.renarde.htmx.HxController;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.truthencode.ddo.codex.model.xref.CategoryData;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

public class Categories extends HxController {
    private static final Logger log = LoggerFactory.getLogger(Categories.class);
    @Inject
    Validator validator;

    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance index(List<CategoryData> categories, Long currentId, String search);

        public static native TemplateInstance index$childSearch(List<CategoryData> categories, Long currentId);
    }


    public TemplateInstance index(@RestQuery("id") Long id, @RestQuery("search") String search) {
        // should paginate this?
        log.info("index id:{} search:{}", id, search);
        var searchCat = CategoryData.search(search);
        if (isHxRequest()) {
            log.info("index hx request from Category.index");
            return Templates.index$childSearch(searchCat, id);
        }
        log.info("index called (non-hx)");
        List<CategoryData> categories = CategoryData.listAll();
        // render the index template
        return Categories.Templates.index(categories, null, null);
    }

    @POST
    public TemplateInstance search(@RestForm @NotBlank String search) {
        log.info("search name:{}", search);
        if (search == null || search.isBlank()) {
            flash("error", "Search term cannot be blank");
            return index(null, null);
        }
        // redirect to index with search query
        return index( null, search);
    }

    @POST
    public TemplateInstance save(@RestForm @NotBlank String name) {
        log.info("save name:{}", name);
        if (name == null || name.isBlank()) {
            flash("error", "Name cannot be blank");
            return index(null, null);
        }
        CategoryData category = new CategoryData();
        category.name = name;
        Set<ConstraintViolation<PanacheEntityBase>> violations = validator.validate(category);
        if (!violations.isEmpty()) {
            flash("error", "Name cannot be blank");
            return index(null, null);
        }
        category.persist();
        flash("success", "Category saved");
        return index(null, null);
    }

    @POST
    public TemplateInstance edit(@RestForm Long id) {
        log.info("edit id:{}", id);
        CategoryData category = CategoryData.findById(id);
        if (category == null) {
            flash("error", "Category not found");
            return index(null, null);
        }
        if (isHxRequest()) {
            log.info("hx request from Category.edit");
            return Templates.index$childSearch(category.children.stream().toList(), id);
        }
        // render the index template
        log.info("edit called (non-hx) for category:{}", category.name);
        return Categories.Templates.index(category.children.stream().toList(), id, null);
    }

    @POST
    public void create(@RestForm @NotBlank String name, @RestForm String description, @RestForm String parentId) {

        // avoid duplicate names
        CategoryData category = CategoryData.findByName(name);
        if (category != null) {
            flash("error", "Category already exists");
            index(null, null);
            return;
        }
        category = new CategoryData();
        category.name = name;
        category.description = description;

        if (parentId != null && !parentId.isBlank()) {
            try {
                var pid = Long.parseLong(parentId);
                var parent = (CategoryData) CategoryData.findById(pid);

                if (parent != null) {
                    category.parent = parent;
                } else {
                    flash("error", "Parent category not found");
                }
            } catch (NumberFormatException e) {
                flash("error", "[" + parentId + "] is not a valid category id");
            }
        }
        // validate the Category
        // TODO: this is not working
        Set<ConstraintViolation<CategoryData>> violations = validator.validate(category);
        if (violations.isEmpty()) {
            category.persist();
            flash("message", "Category created");
        } else {
            flash("error", "Category not created");
        }
        index(null, null);
    }


    @DELETE
    public void delete(Long id) {
        // find the Category
        CategoryData category = PanacheEntityBase.findById(id);
        notFoundIfNull(category);
        // delete it
        category.delete();
        // send loving message
        flash("message", "Task deleted");
        // redirect to index page
        index(null, null);
    }
}
