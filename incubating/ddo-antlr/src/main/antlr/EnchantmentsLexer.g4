lexer grammar EnchantmentsLexer;

LineBreak
  :  ('\r'? '\n'
  |  '\r') -> skip
  ;


//VALIDID : ENCHANTMENT | UNKNOWN_ID;

//prefixedDecl : NUM ENCHANTMENT | ENCHANTMENT PCT | ENCHANTMENT + NUM | ENCHANTMENT;

// Known, named enchantments
ENCHANTMENT : FORT | AUGMENTSLOT | ENHANCEMENT_BONUS | SHELTERING;

AUGMENTSLOT : AUGMENTCOLOR ' ' LBL_AUGMENT_SLOT ' ' AUGMENTOPTION;
LBL_AUGMENT_SLOT : 'Augment Slot:';
ENHANCEMENT_BONUS : 'Enhancement Bonus';
FORT : 'Fortification';
SHELTERING : SHELTERTYPE ' ' 'Sheltering';
SHELTERTYPE : 'Physical and Magical' | 'Physical' | 'Magical';
AUGMENTCOLOR : 'Blue' | 'Orange' | 'Yellow' | 'Red' | 'Green' | 'Purple' | 'Colorless';
AUGMENTOPTION: EmptyAugmentSlot | FilledAugmentSlot;

FilledAugmentSlot: 'todo:MARKER '; // can be empty or filled with any named or crafted augment
EmptyAugmentSlot: 'Empty';
//r  : 'hello' ID ;         // match keyword hello followed by an identifier
//ID : [a-zA-Z]+ ;             // match lower-case identifiers
WS : [ \t\r\n]+ -> channel(HIDDEN) ; // skip spaces, tabs, newlines
PCT : NUM + '%';          // SIGNED percent
NUM : SIGN + UNUM;        // signed num

SIGN : '+' | '-';
fragment UNUM : [0-9]+;            // unsigned numbers


// catch-all word blobs MUST LIST AFTER OTHER RULES
UNKNOWN_ID : MULTI_WORDS | SIMPLE_WORD | MULTI_WORD;
fragment MULTI_WORDS : MULTI_WORD + (' ' + SIMPLE_WORD);
fragment MULTI_WORD: SIMPLE_WORD + ' ' + SIMPLE_WORD;
fragment SIMPLE_WORD: [A-Za-z] + [a-z]+;
