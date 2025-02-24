package io.truthencode.ddo.codex.rest;

import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.truthencode.ddo.codex.model.xref.CharacterClass;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.List;

public class Classes extends Controller {
    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance index(List<CharacterClass> classes);
        public static native TemplateInstance view(CharacterClass characterClass);

    }

    public TemplateInstance index() {
        // list every CharacterClass
        return Templates.index(CharacterClass.listAll());
    }

    public TemplateInstance view(@RestQuery Long id) {
        // find the CharacterClass
        CharacterClass characterClass = CharacterClass.findById(id);
        notFoundIfNull(characterClass);
        // render the index template
        return Templates.view(characterClass);
    }


}
