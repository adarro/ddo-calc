package io.truthencode.ddo.etl.internal;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

/**
 * Generates SQL insert statements for the given table name based on current existing values
 */
@ApplicationScoped
public class FeatSqlGenerator {
    /**
     * Generates a SQL insert statement using the given template
     * @param featTemplate SQL template for the given table
     */
    public FeatSqlGenerator(@TemplatePath("feat.ftl")
                            Template featTemplate) {
        this.featTemplate = featTemplate;
    }

    /**
     * Generates a SQL insert statement for the given table name based on current existing values
     */
    Template featTemplate;

    /**
     * Generates a SQL insert statement for the given table name based on current existing values
     * @param table destination SQL table
     * @param columns list of column identifiers
     * @param feats collection of feats to insert
     * @return SQL DDL for inserting the values
     * @throws TemplateException when bad stuff happens
     * @throws IOException when we can't tell about input or output
     */
    public String genSqlTemplate(String table, List<String> columns, List<SqlInsertGenerator.FeatItem> feats) throws TemplateException, IOException {
        StringWriter stringWriter = new StringWriter();

        Map<String, Object> test2 = Map.ofEntries(
            entry("table",table),
            entry("columns", columns),
            entry("feats", feats)
        );

       featTemplate.process(test2,stringWriter);
        return stringWriter.toString();
    }
}
