package io.truthencode.ddo.codex.rest;

import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.truthencode.ddo.codex.model.xref.Race;
import io.truthencode.ddo.codex.model.xref.RaceFamily;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.POST;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Races extends Controller {
    private static final Logger log = LoggerFactory.getLogger(Races.class);


    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance index(List<Race> races);
    }


    public TemplateInstance index() {
        // list every Race
        // I feel that we should gracefully handle the case where we can't find the Race
        // Perhaps an empty list and flash a warning instead of failing to render the page.
        List<Race> races = Race.listAll(); //.await().atMost(Duration.ofSeconds(10)).stream().map(Race.class::cast).toList();

        // render the index template
        return Templates.index(races);
    }


    @POST
    public void delete(@RestPath Long id) {
        // find the Race
        Race race = Race.findById(id);
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

        // find the Race
        Race race = Race.findById(id);
        notFoundIfNull(race);

        // create a new Race
        var familyQ = RaceFamily.find(family);
        RaceFamily fam = familyQ.firstResult();
        notFoundIfNull(fam);


        race.family = fam;
        race.persist();
        // send loving message
        flash("message", "Task added");
        // redirect to index page
        index();
    }
}
