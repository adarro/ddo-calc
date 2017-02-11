package org.aos.ddo.model.race

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.model.attribute._
import org.aos.ddo.model.misc._
import org.aos.ddo.support.SearchPrefix

/**
  * Represents a DDO Race [http://ddowiki.com/page/Races]
  */
sealed trait Race extends EnumEntry {
  self: Availability with AttributeModifier =>
  //  val initialStrength: RangedType[Strength]
  //  val initialDexterity: RangedType[Dexterity]
  //  val initialConstitution: RangedType[Constitution]
  //  val initialIntelligence: RangedType[Intelligence]
  //  val initialWisdom: RangedType[Wisdom]
  //  val initialCharisma: RangedType[Charisma]
}

/*
Race 	Type* 	   STR 	   DEX 	   CON 	   INT 	   WIS 	   CHA 	Racial Ability Modifiers 	Racial Enhancement Tree
Ability Modifiers
Bladeforged 	I 	8-18 	6-16 	10-20 	8-18 	6-16 	8-18 	Con +2, Dex -2, Wis -2 	+2 Con
Deep Gnome (Svirfneblin) 	I 	6-16 	8-18 	8-18 	10-20 	10-20 	6-16 	Int +2, Wis +2, Str -2, Cha -2 	+2 (total) Wis +/or Int
Drow Elf 	F** 	8-18 	10-20 	6-16 	10-20 	8-18 	10-20 	Dex +2, Int +2, Cha +2, Con -2 	+2 (total) Dex, Int +/or Cha
Dwarf 	F 	8-18 	8-18 	10-20 	8-18 	8-18 	6-16 	Con +2, Cha -2 	+2 Con
Elf 	F 	8-18 	10-20 	6-16 	8-18 	8-18 	8-18 	Dex +2, Con -2 	+2 Dex
Gnome 	P 	6-16 	8-18 	8-18 	10-20 	8-18 	8-18 	Inte +2, Str -2 	+2 Int
Halfling 	F 	6-16 	10-20 	8-18 	8-18 	8-18 	8-18 	Dex +2, Str -2 	+2 Dex
Half-Elf 	P 	8-18 	8-18 	8-18 	8-18 	8-18 	8-18 	(none) 	As Elf or Human (your choice), but not both
Half-Orc 	P 	10-20 	8-18 	8-18 	6-16 	8-18 	6-16 	Str +2, Int -2, Cha -2 	+2 Str
Human 	F 	8-18 	8-18 	8-18 	8-18 	8-18 	8-18 	(none) 	your choice of +1 any stat, & +1 to a different stat
Morninglord (Sun Elf) 	I 	8-18 	8-18 	6-16 	10-20 	8-18 	8-18 	Int +2, Con -2 	+2 Int
Purple Dragon Knight 	I 	8-18 	8-18 	8-18 	8-18 	8-18 	8-18 	(none) 	your choice of +1 any stat, & +1 to a different stat
Shadar-kai 	I 	8-18 	10-20 	8-18 	8-18 	8-18 	6-16 	Dex +2, Cha -2 	+2 (total) Dex, Int +/or Wis
Warforged 	P 	8-18 	8-18 	10-20 	8-18 	6-16 	6-16 	Con +2, Wis -2, Cha -2 	+2 Con
*/
object Race extends Enum[Race] with SearchPrefix {
    case object Bladeforged extends Race with AttributeModifierInit with IconicFeature with DexterityModifier with ConstitutionModifier with WisdomModifier {
    override protected lazy val intModifierDexterity: Int = -2
    override protected lazy val intModifierConstitution: Int = 2
    override protected lazy val intModifierWisdom: Int = -2
  }

  case object DeepGnome extends Race with AttributeModifierInit
    with IconicFeature with IntelligenceModifier with WisdomModifier with StrengthModifier with CharismaModifier {
    override protected lazy val intModifierIntelligence: Int = 2
    override protected lazy val intModifierWisdom: Int = 2
    override protected lazy val intModifierStrength: Int = -2
    override protected lazy val intModifierCharisma: Int = -2
  }

  // Svirfneblin
  case object DrowElf extends Race with AttributeModifierInit
    with FavorFeature with DexterityModifier with IntelligenceModifier with CharismaModifier with ConstitutionModifier {

    override protected lazy val intModifierDexterity: Int = 2
    override protected lazy val intModifierIntelligence: Int = 2
    override protected lazy val intModifierCharisma: Int = 2
    override protected lazy val intModifierConstitution: Int = -2
  }

  case object Dwarf extends Race with AttributeModifierInit with FreeToPlayFeature with ConstitutionModifier with CharismaModifier {
    override protected lazy val intModifierConstitution: Int = 2
    override protected lazy val intModifierCharisma: Int = -2
  }

  case object Elf extends Race with AttributeModifierInit with FreeToPlayFeature with DexterityModifier with ConstitutionModifier {
    override protected lazy val intModifierDexterity: Int = 2
    override protected lazy val intModifierConstitution: Int = -2
  }

  case object Gnome extends Race with AttributeModifierInit with PremiumFeature with IntelligenceModifier with StrengthModifier {
    override protected lazy val intModifierIntelligence: Int = 2
    override protected lazy val intModifierStrength: Int = -2
  }

  case object Halfling extends Race with AttributeModifierInit with FreeToPlayFeature with DexterityModifier with StrengthModifier {
    override protected lazy val intModifierDexterity: Int = 2
    override protected lazy val intModifierStrength: Int = -2
  }

  case object HalfElf extends Race with AttributeModifierInit with PremiumFeature with DefaultAttributeModifier

  case object HalfOrc extends Race with AttributeModifierInit with PremiumFeature with StrengthModifier with IntelligenceModifier with CharismaModifier {
    override protected lazy val intModifierStrength: Int = 2
    override protected lazy val intModifierIntelligence: Int = -2
    override protected lazy val intModifierCharisma: Int = -2
  }

  case object Human extends Race with AttributeModifierInit with FreeToPlayFeature with DefaultAttributeModifier

  case object Morninglord extends Race with AttributeModifierInit with IconicFeature with IntelligenceModifier with ConstitutionModifier {
    override protected lazy val intModifierIntelligence: Int = 2
    override protected lazy val intModifierConstitution: Int = -2
  }

  case object PurpleDragonKnight extends Race with AttributeModifierInit with IconicFeature with DefaultAttributeModifier

  case object Shadarkai extends Race with AttributeModifierInit with IconicFeature with DexterityModifier with CharismaModifier {
    override protected lazy val intModifierDexterity: Int = 2
    override protected lazy val intModifierCharisma: Int = -2
  }

  case object Warforged extends Race with AttributeModifierInit with PremiumFeature with ConstitutionModifier with WisdomModifier with CharismaModifier {
    override protected lazy val intModifierConstitution: Int = 2
    override protected lazy val intModifierWisdom: Int = -2
    override protected lazy val intModifierCharisma: Int = -2
  }

  override def values: Seq[Race] = findValues

  /**
    * An optional delimiter such as a colon when overridden.
    * By default, this is set to Option.None
    *
    * @return The delimiter, if it exists.
    */
  override def delimiter: Option[String] = Some(":")

  override def searchPrefixSource: String = "Race"
}
