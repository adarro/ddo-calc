parser grammar EnchantmentsParser;

@header {package io.truthencode.ddo.grammar.antlr; }

options { tokenVocab = EnchantmentsLexer;
 }
// roman numerals taken fro PHP example BSD https://github.com/gabriele-tomassetti/getting-started-antlr-php/blob/4a7ce18e63766087f50dab881c99000124f2016c/RomanNumerals.g4
import RomanNumeralsParser;

parseEnchantment returns [List<String> row]
@init {
 $row = new ArrayList<String>();
}
  :  (idDecl {$row.add($idDecl.text);})* (STMTEND | EOF)
  ;

//thing : PCT ENCHANTMENT;

//idDecl : PCT;
idDecl : numberOrPctPrefix? enchantmentType numberOrRomanSuffix?;
//idDecl : enchantmentType numberOrRomanSuffix?;
//|  enchantmentType numberOrRomanSuffix | enchantmentType;

numberOrPctPrefix : NUMERIC;
//numberOrPctPrefix : (NUM | PCT);
numberOrRomanSuffix : NUMBER_WITH_OPTIONS | NUMERIC | numeral;

enchantmentType : (ITEM_ENCHANTMENT | slug);

slug : numberOrPctPrefix? UNKNOWN_ENCHANTMENT numberOrRomanSuffix?;


