package io.truthencode.ddo.etl.internal;

import io.jstach.jstachio.JStachio;
import io.quarkus.test.junit.QuarkusTest;
import io.truthencode.ddo.model.effect.TriggerEvent;
import io.truthencode.ddo.test.util.FileUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TriggerEventSqlTemplateGeneratorTest {

    private static final Logger logger = Logger.getLogger(TriggerEventSqlTemplateGeneratorTest.class);

    String loadExpectedSql(String filePath) {
        var f = FileUtil.loadResource(filePath);

        String content = "";
        try {
            content = new String(Files.readAllBytes(f.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * This test is brittle as we need to update it with every new trigger.
     * We should sample the database and generate the expected SQL for that data instead.
     */
    @Test
    @DisplayName("Triggers should be exportable to SQL")
    void testTriggerTemplateGeneration() {
        var trigs = TriggerEvent.asJava().stream().map(SqlInsertGenerator.TriggerItem::new).toList();
        logger.warnf("val size %d aj size %d", TriggerEvent.values().length(), trigs.size());
        assertFalse(trigs.isEmpty());
        logger.warn(trigs.size());
        var sql = new SqlInsertGenerator.TriggerEventSqlTemplate("trigger_table", List.of("name", "active", "passive"), trigs);
        var actual = JStachio.render(sql).strip();
        logger.warn(actual);
        var expected = loadExpectedSql("expected_trigger.sql").trim();
        assertEquals(expected, actual);
    }

}
