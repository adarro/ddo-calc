package io.truthencode.ddo.codex.rest;

import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.truthencode.ddo.codex.model.xref.RaceFamily;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.POST;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RaceFamilies extends Controller {
    private static final Logger log = LoggerFactory.getLogger(RaceFamilies.class);


    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance index(List<RaceFamily> families);
        public static native TemplateInstance view(RaceFamily family);
    }


    public TemplateInstance index() {
        // list every RaceFamily
        // I feel that we should gracefully handle the case where we can't find the RaceFamily
        // Perhaps an empty list and flash a warning instead of failing to render the page.
        List<RaceFamily> families = RaceFamily.listAll(); //.await().atMost(Duration.ofSeconds(10)).stream().map(RaceFamily.class::cast).toList();

        // render the index template
        return Templates.index(families);
    }

    public TemplateInstance view(@RestPath Long id) {
        // find the RaceFamily
        RaceFamily family = RaceFamily.findById(id);
        notFoundIfNull(family);
        // render the index template
        return Templates.view(family);
    }


    @POST
    public void delete(@RestPath Long id) {
        // find the RaceFamily
        RaceFamily race = RaceFamily.findById(id);
        notFoundIfNull(race);
        // delete it
        race.delete();
        // send loving message
        flash("message", "Task deleted");
        // redirect to index page
        index();
    }


    @POST
    public void addFamily(@RestPath Long id, @NotBlank @RestForm String family) {
        // check if there are validation issues
        if (validationFailed()) {
            // go back to the index page
            index();
        }

        // find the RaceFamily
        RaceFamily fam = RaceFamily.findById(id);

        if (fam == null) {
            fam = new RaceFamily();
            fam.name = family;
            fam.persist();
        }

        flash("message", "Family added added");
        // redirect to index page
        index();
    }
}
