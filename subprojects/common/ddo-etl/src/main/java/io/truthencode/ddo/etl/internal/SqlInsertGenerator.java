package io.truthencode.ddo.etl.internal;

import io.jstach.jstache.JStache;
import io.truthencode.ddo.enhancement.BonusType;
import io.truthencode.ddo.model.effect.ActiveEvent;
import io.truthencode.ddo.model.effect.PassiveEvent;
import io.truthencode.ddo.model.effect.TriggerEvent;
import io.truthencode.ddo.model.feats.ActiveFeat;
import io.truthencode.ddo.model.feats.Feat;
import io.truthencode.ddo.model.feats.Passive;
import io.truthencode.ddo.model.stats.BasicStat;
import io.truthencode.ddo.model.stats.Category;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/**
 * Template for generating SQL insert statements from enumeration classes
 */
public class SqlInsertGenerator {

    /**
     * Template for generating SQL insert statements from enumeration classes
     * Contains categories used for grouping statistics such as General Combat, Saves, etc.
     *
     * @param <T> enhanced Enumeration properties class
     */
    public static class CategoryItem<T extends Category> {
        private final T value;

        /**
         * Main Constructor for Category Bean
         *
         * @param value value of the enumeration
         */
        public CategoryItem(T value) {
            this.value = value;
        }

        /**
         * The shortname key used for the enumeration
         *
         * @return the shortname key
         */
        public String name() {
            return value.categoryId();
        }

        /**
         * The display text used for the enumeration
         *
         * @return the display text
         */
        public String displayText() {
            return value.catName();
        }
    }


    /**
     * Template for generating SQL insert statements from enumeration classes
     * Contains basic statistic properties
     *
     * @param <T> enhanced Enumeration properties class representing a basic statistic
     */
    public static class BasicStatItem<T extends BasicStat> {
        private final T value;

        /**
         * Main Constructor for Basic Stat Bean
         * @param value value of the enumeration
         */
        public BasicStatItem(T value) {
            this.value = value;
        }

        T value() {
            return value;
        }

        /**
         * The shortname key used for the enumeration
         *
         * @return the shortname key
         */
        public String name() {
            return value.entryName();
        }

        /**
         * The display text used for the enumeration
         *
         * @return the display text
         */
        public String displayText() {
            return value.displayText();
        }
    }

    /**
     * POJO model for a trigger event
     *
     * @param <T> the trigger event scala model
     */
    public static class TriggerItem<T extends TriggerEvent> {
        private final T value;

        /**
         * Constructor for a trigger event
         * @param value the trigger event
         */
        public TriggerItem(T value) {
            this.value = value;
        }

        T value() {
            return value;
        }

        /**
         * The shortname key used for the enumeration
         * @return the shortname key
         */
        public String name() {
            return value.entryName();
        }

        /**
         * Flag indicating if the property has an active element.
         * @return true if the property has an active element.
         */
        public boolean isActive() {
            return value instanceof ActiveEvent;
        }


        /**
         * Flag indicating if the property has a passive element.
         * @return true if the property has a passive element.
         */
        public boolean isPassive() {
            return value instanceof PassiveEvent;
        }
    }

    /**
     * POJO model for a bonus type
     *
     * @param <T> the bonus type scala model
     */
    public static class BonusTypeItem<T extends BonusType> {
        private final T value;

        /**
         * Constructor for a bonus type
         * @param value the bonus type
         */
        public BonusTypeItem(T value) {
            this.value = value;
        }

        /**
         * The shortname key used for the enumeration
         * @return the shortname key
         */
        public String name() {
            return value.entryName();
        }
    }

    /**
     * POJO model for a feat
     */
    public static class FeatItem {

        private final Feat value;

        /**
         * Constructor for a feat
         * @param value enhanced enumeration model
         */
        public FeatItem(Feat value) {
            this.value = value;
        }

        /**
         * The shortname key used for the enumeration
         * @return the shortname key
         */
        public String name() {
            return value.entryName();
        }

        /**
         * The display text used for the enumeration
         * @return the display text
         */
        public String description() {
            return value.displayText();
        }

        /**
         * SQL Sanitized version of the description
         * @return the sanitized description
         */
        public String safeDesc() {
            return description().replace("'", "''");
        }

        /**
         * Flag indicating if the property has an active element.
         * @return true if the property has an active element.
         */
        public boolean isActive() {
            return value instanceof ActiveFeat;
        }

        /**
         * Flag indicating if the property has a passive element.
         * @return true if the property has a passive element.
         */
        public boolean isPassive() {
            return value instanceof Passive;
        }

        /**
         * Flag indicating if the property has a passive element.
         * @return String values of "Active" or "Passive" based on the value
         */
        public List<String> usages() {
            var vals = new ArrayList<String>();
            if (isActive())
                vals.add("Active".toUpperCase(Locale.getDefault()));
            if (isPassive())
                vals.add("Passive".toUpperCase(Locale.getDefault()));
            return new HashSet<>(vals).stream().toList();
        }
    }

    /**
     * POJO model for a Trigger Event template values
     * @param table the table name
     * @param columns the columns in the table
     * @param triggers a collection of trigger events
     */
    @JStache(path = "sql_templates/TriggerEvent.mustache")
    public record TriggerEventSqlTemplate(String table, List<String> columns,
                                          List<TriggerItem<TriggerEvent>> triggers) {
    }

    /**
     * POJO model for a Basic Event template values
     * @param table the table name
     * @param columns the columns in the table
     * @param categories a collection of basic event categories
     */
    @JStache(path = "sql_templates/BasicCategory.mustache")
    public record BasicStatEventSqlTemplate(String table, List<String> columns,
                                            List<BasicStatItem<BasicStat>> categories) {
    }

    /**
     * POJO model for a Category template values
     * @param table the table name
     * @param columns   the columns in the table
     * @param categories a collection of categories
     */
    @JStache(path = "sql_templates/BasicCategory.mustache")
    public record CategorySqlTemplate(String table, List<String> columns, List<CategoryItem<Category>> categories) {
    }

    /**
     * POJO model for a Bonus Type template values
     * @param table the table name
     * @param columns the columns in the table
     * @param bonusTypes a collection of bonus types
     */
    @JStache(path = "sql_templates/BonusType.mustache")
    public record BonusTypeSqlTemplate(String table, List<String> columns, List<BonusTypeItem<BonusType>> bonusTypes) {
    }

}
