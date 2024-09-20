package io.truthencode.ddo.grammar.antlr;

import org.junit.jupiter.api.DisplayName;

public class BasicTestJavaSamePackage {

    @DisplayName("Generated ANTLR parsers can be referenced from the same Java package")
    public void referenceParserFromJavaSamePackage() {
        @SuppressWarnings("unused")
        io.truthencode.ddo.grammar.antlr.EnchantmentsParser parser = null;
    }
}
