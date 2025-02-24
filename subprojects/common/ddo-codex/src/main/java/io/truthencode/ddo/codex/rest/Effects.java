package io.truthencode.ddo.codex.rest;

import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.truthencode.ddo.codex.model.xref.EffectList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Effects extends Controller {
    private static final Logger log = LoggerFactory.getLogger(Effects.class);


    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance index(List<EffectList> effects);
    }


    public TemplateInstance index() {
        // should paginate this
        List<EffectList> effects = EffectList.listAll(); //.await().atMost(Duration.ofSeconds(10)).stream().map(Race.class::cast).toList();

        // render the index template
        return Effects.Templates.index(effects);
    }

}
