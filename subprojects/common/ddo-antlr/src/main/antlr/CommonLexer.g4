lexer grammar CommonLexer;

import RomanNumeralsLexer;
//STMTEND : NEWLINE+ | EOF+;

//ID : [a-zA-Z]+ ;             // match lower-case identifiers
WS : [ \t\r\n]+ -> channel(HIDDEN) ; // skip spaces, tabs, newlines
NUMBER_WITH_OPTIONS : NUMERIC ' ' OR ' ' NUMERIC;
NUMERIC : PCT | NUM;
PCT : NUM + '%';          // SIGNED percent
NUM : (SIGN + UNUM) | UNUM;        // signed num
SELECTOR_WORD : OR;

OR: 'or';
SIGN : '+' | '-';
fragment UNUM : [0-9]+;            // unsigned numbers
fragment COLON: ':';
//INDENT : [ \t]+ {System.out.println("INDENT")>} {this.getCharPositionInLine()==0}? ;
// catch-all word blobs MUST LIST AFTER OTHER RULES
UNKNOWN_ID : MULTI_WORDS | MULTI_WORD | SIMPLE_WORD;
fragment MULTI_WORDS : MULTI_WORD + ' ' + MULTI_WORD | MULTI_WORD + (' ' + SIMPLE_WORD);
fragment MULTI_WORD: SIMPLE_WORD + ' ' + SIMPLE_WORD;
fragment SIMPLE_WORD: START_CHARS + ALPHA_CHARS*;
fragment START_CHARS : ALPHA_CHARS;
fragment ALPHA_CHARS : LOWERCASE_LETTERS | NON_ROMAN_LETTERS | ROMAN_LETTERS;
fragment LOWERCASE_LETTERS : [a-z];
fragment NON_ROMAN_LETTERS : [ABEFGHJKNOPQRSTUWYZ];
fragment ROMAN_LETTERS : [IVXLCDM];
fragment NEWLINE   : '\r' '\n' | '\n' | '\r';