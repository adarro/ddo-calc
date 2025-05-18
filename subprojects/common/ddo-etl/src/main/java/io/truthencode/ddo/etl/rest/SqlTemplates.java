package io.truthencode.ddo.etl.rest;

import freemarker.template.TemplateException;
import io.jstach.jstachio.JStachio;
import io.truthencode.ddo.enhancement.BonusType;
import io.truthencode.ddo.etl.internal.FeatSqlGenerator;
import io.truthencode.ddo.etl.internal.SqlInsertGenerator;
import io.truthencode.ddo.model.effect.TriggerEvent;
import io.truthencode.ddo.model.feats.Feat;
import io.truthencode.ddo.model.stats.BasicStat;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.jboss.resteasy.reactive.RestQuery;

import java.io.IOException;
import java.util.List;

/**
 * REST Endpoint for generating SQL Insert Statements based on enumeration classes
 */
@Path("sql")
public class SqlTemplates {

    @Inject
    FeatSqlGenerator featGenerator;


    /**
     * Generates a SQL insert statement for the given table name based on current existing values
     *
     * @param tableName SQL Table Name (i.e. PUBLIC.trigger_table)
     * @return DDL for SQL INSERT Statements
     */
    @GET
    @Path("triggers")
    @Operation(
        summary = "Get the available trigger events",
        description = "Get the available trigger events in SQL Insert Statements"
    )
    @Parameters(
        value = {
            @Parameter(name = "tableName", description = "SQL Table Name (i.e. PUBLIC.trigger_table)", required = true, example = "public.trigger_table")
        }
    )
    public String triggerEffects(@RestQuery String tableName) {
        var trigs = TriggerEvent.asJava().stream().map(SqlInsertGenerator.TriggerItem::new).toList();
        var sql = new SqlInsertGenerator.TriggerEventSqlTemplate("trigger_table", List.of("name", "active", "passive"), trigs);

        return JStachio.render(sql);

    }

    /**
     * Generates a SQL insert statement for the Feats table on current existing values
     *
     * @param tableName SQL Table Name (i.e. PUBLIC.feat)
     * @return DDL for SQL INSERT Statements
     */
    @GET
    @Path("Feats")
    @Operation(
        summary = "Get the available Feats",
        description = "Generates SQL Insert Statements for existing feats"
    )
    @Parameters(
        value = {
            @Parameter(name = "tableName", description = "SQL Table Name (i.e. PUBLIC.feat)", required = true, example = "public.feat")
        }
    )
    public String feats(@RestQuery String tableName) {
        var feats = Feat.asJava().stream().map(SqlInsertGenerator.FeatItem::new).toList();
        String sql = null;
        try {
            sql = featGenerator.genSqlTemplate(tableName, List.of("name", "description"), feats);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (sql == null) {
            // do nothing atm
        }
        return sql;

    }


    /**
     * Generates a SQL insert statement for the given table name based on current existing values
     *
     * @param tableName SQL Table Name (i.e. PUBLIC.basic_stat)
     * @return DDL for SQL INSERT Statements
     */
    @GET
    @Path("BonusTypes")
    @Operation(
        summary = "Get the available bonus types",
        description = "Get the available bonus types in SQL Insert Statements"
    )
    @Parameters(
        value = {
            @Parameter(name = "tableName", description = "SQL Table Name (i.e. PUBLIC.trigger_table)", required = true, example = "public.bonus_type")
        }
    )
    public String bonusTypes(@RestQuery String tableName) {
        var data = BonusType.asJava().stream().map(SqlInsertGenerator.BonusTypeItem::new).toList();
        var sql = new SqlInsertGenerator.BonusTypeSqlTemplate(tableName, List.of("name"), data);
        return JStachio.render(sql);
    }

    /**
     * Generates a SQL insert statement for the given table name based on current existing values of the BasicStats enum.
     *
     * @param tableName SQL Table Name (i.e. PUBLIC.trigger_table)
     * @return DDL for SQL INSERT Statements
     */
    @GET
    @Path("BasicCategories")
    public String basicCategories(@RestQuery String tableName) {
        var data = BasicStat.asJava().stream().map(SqlInsertGenerator.BasicStatItem::new).toList();
        var sql = new SqlInsertGenerator.BasicStatEventSqlTemplate(tableName, List.of("name", "display_name"), data);

        return JStachio.render(sql);

    }

    /**
     * Generates a SQL insert statement for the given table name based on current existing values of the BasicStats enum.
     *
     * @param tableName SQL Table Name (i.e. PUBLIC.trigger_table)
     * @return DDL for SQL INSERT Statements
     */
    @GET
    @Path("Categories")
    @Operation(
        summary = "Get the available categories",
        description = "Generate SQL Insert Statements based on the existing categories"
    )
    @Parameters(
        value = {
            @Parameter(name = "tableName", description = "SQL Table Name (i.e. PUBLIC.category)", required = true, example = "public.category")
        }
    )
    public String categories(@RestQuery String tableName) {
        var data = BasicStat
            .categoriesJava()
            .stream()
            .map(SqlInsertGenerator.CategoryItem::new).toList();


        var sql = new SqlInsertGenerator.CategorySqlTemplate(tableName, List.of("name", "display_name"), data);
        return JStachio.render(sql);
    }

}

