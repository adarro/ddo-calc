package io.truthencode.ddo.codex.rest;

import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

public class Entities extends Controller {
    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance index();
    }


    public TemplateInstance index() {

        // render the index template
        return Templates.index();
    }
}
