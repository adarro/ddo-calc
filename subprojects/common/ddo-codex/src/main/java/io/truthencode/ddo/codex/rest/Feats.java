package io.truthencode.ddo.codex.rest;

import io.quarkiverse.renarde.Controller;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.truthencode.ddo.codex.model.xref.FeatData;

import java.util.List;

public class Feats extends Controller {
    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance index(List<FeatData> feats);
        public static native TemplateInstance view(FeatData feat);
    }

    public TemplateInstance index() {
        // list every Feat
        List<FeatData> feats = PanacheEntity.listAll();
        // render the index template
        return Templates.index(feats);
    }

    public TemplateInstance view(Long id) {
        // find the Feat
        FeatData feat = FeatData.findById(id);
        notFoundIfNull(feat);
        // render the index template
        return Templates.view(feat);
    }
}
