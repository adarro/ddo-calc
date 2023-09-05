parser grammar EnchantmentsParser;

@header { package io.truthencode.ddo.grammar.antlar; }

options { tokenVocab = EnchantmentsLexer; }

parse returns [List<String> row]
@init {
 $row = new ArrayList<String>();
}
  :  (idDecl {$row.add($idDecl.text);})+ (LineBreak | EOF)
  ;

//thing : PCT ENCHANTMENT;

idDecl : PCT enchantmentType | enchantmentType PCT | NUM enchantmentType |  enchantmentType NUM | enchantmentType;

enchantmentType : (ENCHANTMENT | UNKNOWN_ID);