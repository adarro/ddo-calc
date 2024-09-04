lexer grammar EnchantmentsLexer;

@header {package io.truthencode.ddo.grammar.antlr; }
/* Lexer */

//import CommonLexer;

LineBreak
  :  ('\r'? '\n'
  |  '\r') -> skip
  ;




// Known, named enchantments
ITEM_ENCHANTMENT : FALSE_LIFE | HEALING_AMPLIFICATION | CONCEALMENT | SAVES | UPGRADABLE_FLAG | ELEMENTAL_ABSORBTION | LORE | AUGMENTATION | FORT | AUGMENTSLOT | ENHANCEMENT_BONUS | SHELTERING | DAMAGE_TYPE | MYTHIC_BOOST | CONTINUOUS_SPELL_POWER | SPELL_SCHOOL_FOCUS;

UNKNOWN_ENCHANTMENT : MULTI_WORDS | MULTI_WORD | SIMPLE_WORD;
// TODO: grammar - Unique Item effects
// https://ddowiki.com/page/Category:Unique_item_enchantments

// TODO: grammar - Set bonuses
// https://ddowiki.com/page/Named_item_sets
// Also needed for filigree sets
// Epic Elemental Evil Set


STMTEND : NEWLINE* | NEWLINE+;

// TODO: Upgradable / craftable flags
UPGRADABLE_FLAG : ATTUNED;

ATTUNED: ATTUNED_PREFIX ' ' (ATTUNED_THINGS_WITH_TIER | ATTUNED_THINGS);
ATTUNED_THINGS_WITH_TIER: ATTUNED_PREFIX HEROISM COLON ' ' TIER;
ATTUNED_THINGS: ATTUNED_PREFIX HEROISM;
// TODO: grammar for: Upgradeable Item (Temple of Elemental Evil)
HCOL : ATTUNED_PREFIX HEROISM COLON ' ' TIER;

fragment ATTUNED_PREFIX : 'Attuned ' (TO|BY);
fragment TO : 'to';
fragment BY : 'by';
fragment ATTUNED_HEROISM: HEROISM;

HEROISM : 'Heroism';


fragment TIER : 'Tier';
LORE : SPELL_LORE;



// Spell power / potency et all
// Potency adds + to each spell power. i.e. potency 10 = combustion + 10, glaciation + 10 etc
// Continuous (always on)
CONTINUOUS_SPELL_POWER : POTENCY | CORROSION | DEVOTION | GLACIATION | IMPULSE | MAGNETISM | NULLIFICATION | RADIANCE | RECONSTRUCTION | RESONANCE;
POTENCY : 'Potency';
CORROSION : 'Corrosion';
DEVOTION : 'Devotion';
GLACIATION : 'Glaciation';
IMPULSE : 'Impulse';
MAGNETISM : 'Magnetism';
NULLIFICATION : 'Nullification';
RADIANCE : 'Radiance';
RECONSTRUCTION : 'Reconstruction';
RESONANCE : 'Resonance';


TEMPORARY_SPELL_POWER : INFERNO | EROSION | ARDOR | FREEZE | IMPACT | SPARK | NIHIL | EFFICACY | BRILLIANCE | MENDING | CACOPHONY;
// Temporary (Buff / clicky / potion etc)
INFERNO : 'Inferno';
EROSION : 'Erosion';
ARDOR : 'Ardor';
FREEZE : 'Freeze';
IMPACT : 'Impact';
SPARK : 'Spark';
NIHIL : 'Nihil';
EFFICACY : 'Efficacy';
BRILLIANCE : 'Brilliance';
MENDING : 'Mending';
CACOPHONY : 'Cacophony';

// Healing Amp
HEALING_AMPLIFICATION : BONUS_TYPE ' ' HEALING ' ' AMPLICIATION;


fragment HEALING : 'Healing';
fragment AMPLICIATION : 'Amplification';
fragment POSITIVE_HEALING : 'Positive';
fragment NEGATIVE_HEALING : 'Negative';
fragment REPAIR_HEALING : 'Repair';

// Life / vitality
FALSE_LIFE : (GREATER_LESSER_MODIFIER ' ')? FALSE_LIFE_ID;

FALSE_LIFE_ID: 'False Life';


GREATER_LESSER_MODIFIER : GREATER | LESSER;

fragment GREATER: 'Greater';
fragment LESSER : 'Lesser';

// TODO: craftsmanship (i.e. wonderous)
SPELL_SCHOOL_FOCUS : SPELL_SCHOOL ' ' 'Focus';

// Spell Schools / DC's
SPELL_SCHOOL : ABJURATION | CONJURATION | DIVINATION | ENCHANTMENT | EVOCATION | ILLUSION | NECROMANCY | TRANSMUTATION;
ABJURATION : 'Abjuration';
CONJURATION : 'Conjuration';
DIVINATION : 'Divination';
ENCHANTMENT : 'Enchantment';
EVOCATION : 'Evocation';
ILLUSION : 'Illusion';
NECROMANCY : 'Necromancy';
TRANSMUTATION : 'Transmutation';

// Augmentation
AUGMENTATION : POWER_SOURCE ' ' 'Augmentation';
POWER_SOURCE : 'Arcane' | 'Divine';

ARCHANE_AUGMENTATION : 'Arcane Augmentation';
SPELL_LORE : 'Spell Lore';

// Mythic boost items
// https://ddowiki.com/page/Mythic_bonus
MYTHIC_BOOST : MYTHIC_KEYWORD ' ' MYTHIC_SLOT ' Boost';
fragment MYTHIC_KEYWORD : 'Mythic';
fragment MYTHIC_SLOT : CORE_BODY_SLOT;
// Main body slots, explicitly not including quiver / cosmetics
CORE_BODY_SLOT : 'Armor' | 'Belt' | 'Boot' | 'Cloak' | 'Google' | 'Hands' | 'Head' |  'Neck' | 'Ring' | 'Trinket' | 'Wrist' | MAIN_HAND | OFF_HAND;
HAND_SLOT : MAIN_HAND | OFF_HAND;
fragment MAIN_HAND : 'Weapon';
// TODO: grammer / nomenclature for main / off-hand slots
fragment OFF_HAND : 'Shield' | 'Off-hand';
AUXILLARY_BODY_SLOT: 'Quiver';
// TODO: Cosmetic slots
// Late milestone if any to support it.

// Damage Type (bypasses damage reduction of same) i.e. slashing, magic, silver bypasses DR/Slashing DR/Magic DR/Silver etc.
// Types can be determined by material (silver), shape (slash), magic enhancement (+1 or greater) / elemental or alignment and also 'untyped' which bypasses all DR but DR-
DAMAGE_TYPE : ALIGNMENT_DAMAGE_TYPE | MATERIAL_DAMAGE_TYPE | PHYSICAL_DAMAGE_TYPE | ELEMENTAL_DAMAGE_TYPE | ELEMENTAL_SUB_TYPE;
fragment ALIGNMENT_DAMAGE_TYPE : 'Chaotic' | 'Evil' | 'Good' | 'Lawful';
fragment MATERIAL_DAMAGE_TYPE : 'Silver' | 'Byshek' | 'Adamantine' | 'Cold iron' | 'Crystal' | 'Mithral';
fragment PHYSICAL_DAMAGE_TYPE : 'Slash' | 'Pierce' | 'Bludgeon';
// NOTE: Air, Earth and Water types aren't currently directly implemented,
// i.e. no sword does 'Earth' damage, but might do Acid damage which could be amplified by some Earth affinity.
fragment ELEMENTAL_DAMAGE_TYPE : 'Air' | 'Earth' | FIRE | 'Force' | 'Light' | 'Poison' | SONIC | 'Water' | 'Negative' | 'Positive';
fragment ELEMENTAL_SUB_TYPE : ACID | COLD;

ELEMENTAL_ABSORBTION : ELEMENTS ' ' ABSORPTION;

ABSORPTION: 'Absorption';

fragment ELEMENTS : FIRE | ACID | ELECTRIC | COLD | SONIC;
fragment FIRE : 'Fire';
fragment ACID : 'Acid';
fragment ELECTRIC : 'Electric';
fragment COLD : 'Cold';
fragment SONIC : 'Sonic';

// Augments
AUGMENTSLOT : AUGMENTCOLOR ' ' LBL_AUGMENT_SLOT ' ' AUGMENTOPTION;
LBL_AUGMENT_SLOT : 'Augment Slot:';
ENHANCEMENT_BONUS : 'Enhancement Bonus';

fragment UNCATEGORIZED_ENCHANTMENTS : FORT;

// dodge, concealment, ethereal miss-chance
CONCEALMENT : DISPLACEMENT;
DISPLACEMENT : (LESSER ' ' DISPLACED) | 'DISPLACEMENT';
fragment DISPLACED : 'Displacement';

// Basic one-offs
// Defense
// SAVES
SAVES : RESISTANCE;
fragment RESISTANCE : 'Resistance';
FORT : 'Fortification';
SHELTERING : SHELTERTYPE ' ' 'Sheltering';
SHELTERTYPE : 'Physical and Magical' | 'Physical' | 'Magical';
AUGMENTCOLOR : 'Blue' | 'Orange' | 'Yellow' | 'Red' | 'Green' | 'Purple' | 'Colorless';
AUGMENTOPTION: EmptyAugmentSlot | FilledAugmentSlot;

FilledAugmentSlot: 'todo:MARKER '; // can be empty or filled with any named or crafted augment
EmptyAugmentSlot: 'Empty';

BONUS_TYPE: BONUS_TYPE_COMPETENCE;
BONUS_TYPE_COMPETENCE : 'Competence';
BONUS_TYPE_EXCEPTIONAL : 'Exceptional';
BONUS_TYPE_INSIGHTFUL : 'Insightful';

// ['A', 'B', 'E', 'F', 'G', 'H', 'J', 'K', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'W', 'Y', 'Z'];
// CommonLexer.g4
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