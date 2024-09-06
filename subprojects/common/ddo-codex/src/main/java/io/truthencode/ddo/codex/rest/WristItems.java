package io.truthencode.ddo.codex.rest;

import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.truthencode.ddo.codex.model.WristItem;

import java.util.List;

public class WristItems extends Controller {
    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance index(List<WristItem> wristItems);
    }

    public TemplateInstance index() {
        // list every Wrist
        // I feel that we should gracefully handle the case where we can't find the Wrist
        // Perhaps an empty list and flash a warning instead of failing to render the page.
        List<WristItem> wristItem = WristItem.listAll();
        return Templates.index(wristItem);
    }
}
