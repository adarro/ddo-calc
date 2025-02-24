package io.truthencode.ddo.codex.rest;

import io.quarkiverse.renarde.htmx.HxController;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.truthencode.ddo.codex.model.EntityEntry;
import io.truthencode.ddo.codex.model.EntityList;
import jakarta.inject.Inject;
import jakarta.validation.Validator;
import jakarta.ws.rs.POST;
import org.jboss.logging.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Entities extends HxController {
    private static final Logger logger = Logger.getLogger(Entities.class);
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Entities.class);
    @Inject
    Validator validator;

    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance index(List<EntityEntry> entries);

//        public static native TemplateInstance index$content();
        public static native TemplateInstance index$content(List<EntityEntry> entries);
    }

    private static final EntityList entityList = new EntityList();

    public TemplateInstance index() {
        var el = new EntityList().getEntities();
        log.info("{} entities loaded", el.size());

        if (isHxRequest()) {
            log.info("hx request from Entities.index");
            return concatTemplates( Templates.index$content(el));
        }
        // render the index template
        log.info("index called (non-hx)");
        return Entities.Templates.index(el);
    }

    @POST
    public TemplateInstance viewEntities() {
        logger.info("getEntities called");
        return index();
    }

}
