package io.truthencode.ddo.grammer.antlr;

public enum TextSnippets {
    FORTIFICATION_PCT("Fortification +94%"),
    PHYSICAL_SHELTERING_NUM("Physical Sheltering +19"),
    ARMOR_ENHANCEMENT_BONUS("+5 Enhancement Bonus")
    ;
    private final String rawData;
    TextSnippets(String text) {
        rawData = text;
    }
}
