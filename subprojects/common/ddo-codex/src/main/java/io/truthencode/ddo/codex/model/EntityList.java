package io.truthencode.ddo.codex.model;

import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class EntityList {
    private static final Logger logger = LoggerFactory.getLogger(EntityList.class);
    private List<EntityEntry> entities = Collections.emptyList();

    @Startup
    private void loadEntities() {
        // Hard-coded at least for the short-term.
        // May set up a config file or db entries at a later date.
        try {
            logger.info("starting loadEntities");
            entities = Arrays.asList(
                new EntityEntry("People", "People (dependant ORM) ", new URI("/PersonJavaOrmExample/index")),
                new EntityEntry("Race", "A race", new URI("/Races/index")),
                new EntityEntry("Category", "Effect Categories", new URI("/Categories/index")),
                new EntityEntry("Classes", "Classes", new URI("/Classes/index")),
                new EntityEntry("Skills", "Skills", new URI("/Skills/index")),
                new EntityEntry("Feats", "Feats", new URI("/Feats/index")),
                new EntityEntry("Items", "Items", new URI("/Items/index")),
                new EntityEntry("Spells", "Spells", new URI("/Spells/index")),
                new EntityEntry("Race Families", "Race Families", new URI("/RaceFamilies/index")),
                new EntityEntry("Effects","known effects", new URI("/Effects/index")));



        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    public List<EntityEntry> getEntities() {
        if (entities == null || entities.isEmpty()) {
            logger.info("loading entities from getEntities");
            loadEntities();
        }
        return entities;
    }

}
