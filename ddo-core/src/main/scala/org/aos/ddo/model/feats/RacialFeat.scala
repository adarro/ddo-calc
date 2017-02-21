package org.aos.ddo.model.feats

import enumeratum.Enum
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.StringUtils.Extensions
import org.aos.ddo.support.naming.FriendlyDisplay
import org.aos.ddo.support.requisite.{Inclusion, Requisite}

/**
  * Created by adarr on 2/14/2017.
  */
sealed trait RacialFeat extends Feat with FriendlyDisplay with FeatMatcher {
  self: FeatType with Requisite with Inclusion =>
  val matchFeat: PartialFunction[Feat, RacialFeat] = {
    case x: RacialFeat => x
  }
  val matchFeatById: PartialFunction[String, RacialFeat] = {
    case x: String if RacialFeat.namesToValuesMap.contains(x) =>
      RacialFeat.withNameOption(x) match {
        case Some(y) => y
      }
  }
}
// scalastyle:off number.of.methods
object RacialFeat extends Enum[RacialFeat] with FeatSearchPrefix {

  // Racial Feats
  // Warforged
  case object CompositePlating extends RacialFeat with CompositePlating
  case object LightFortification extends RacialFeat with LightFortification
  case object WarforgedTraits extends RacialFeat with WarforgedTraits
  case object AdamantineBody extends RacialFeat with AdamantineBody
  case object MithralBody extends RacialFeat with MithralBody
  case object ImprovedDamageReduction extends RacialFeat with ImprovedDamageReduction
  case object ImprovedFortification extends RacialFeat with ImprovedFortification
  case object MithralFluidity  extends RacialFeat with MithralFluidity

  // Bladeforged
  case object Bladeforged extends RacialFeat with BladeforgedFeat
  // Human Family
  case object Human extends RacialFeat with HumanFeat
  // Half-orc
  case object HalfOrcBlood extends RacialFeat with HalfOrcBlood
  // Halfling
  case object HalflingAgility extends RacialFeat with HalflingAgility
  case object HalflingBravery extends RacialFeat with HalflingBravery
  case object HalflingKeenEars extends RacialFeat with HalflingKeenEars
  case object HalflingLuck extends RacialFeat with HalflingLuck
  case object HalflingThrownWeaponFocus extends RacialFeat with HalflingThrownWeaponFocus
  // Gnome
  case object GnomishProficiencies extends RacialFeat with GnomishProficiencies
  case object SmallSizeBonus extends RacialFeat with SmallSizeBonus

  // Dwarf
  case object DwarvenStability extends RacialFeat with DwarvenStability
  case object GiantEvasion extends RacialFeat with GiantEvasion
  case object OrcAndGoblinBonus extends RacialFeat with OrcAndGoblinBonus
  case object PoisonSaveBonus extends RacialFeat with PoisonSaveBonus
  case object DwarvenStonecunning extends RacialFeat with DwarvenStonecunning

  // Drow
  case object DrowSpellResistance extends RacialFeat with DrowSpellResistance
  case object SpellSaveBonus extends RacialFeat with SpellSaveBonus
  // Elf
  case object Elf extends RacialFeat with Elf
  case object ElvenKeenSenses extends RacialFeat with ElvenKeenSenses
  case object EnchantmentSaveBonus extends RacialFeat with EnchantmentSaveBonus
  // Half-elf
  case object HalfElfKeenSenses extends RacialFeat with HalfElfKeenSenses {
    override protected def nameSource: String = "Keen Senses".toPascalCase
  }
  case object HalfElfMixedHeritage
      extends RacialFeat
      with HalfElfMixedHeritage {
    override protected def nameSource: String = "Mixed Heritage".toPascalCase
  }
  case object HalfElfSocialGraces extends RacialFeat with HalfElfSocialGraces {
    override protected def nameSource: String = "Social Graces".toPascalCase
  }
  case object ImmunityToSleep extends RacialFeat with ImmunityToSleep

  case object HalfElfDilettanteMonk
      extends RacialFeat
      with HalfElfDilettanteMonk {
    override protected def nameSource: String =
      CharacterClass.Monk.entryName.toPascalCase
  }

  case object HalfElfDilettanteArtificer
      extends RacialFeat
      with HalfElfDilettanteArtificer {
    override protected def nameSource: String =
      CharacterClass.Artificer.entryName.toPascalCase
  }
  case object HalfElfDilettanteBarbarian
      extends RacialFeat
      with HalfElfDilettanteBarbarian {
    override protected def nameSource: String =
      CharacterClass.Barbarian.entryName.toPascalCase
  }

  case object HalfElfDilettanteBard
      extends RacialFeat
      with HalfElfDilettanteBard {
    override protected def nameSource: String =
      CharacterClass.Bard.entryName.toPascalCase
  }

  case object HalfElfDilettanteCleric
      extends RacialFeat
      with HalfElfDilettanteCleric {
    override protected def nameSource: String =
      CharacterClass.Cleric.entryName.toPascalCase
  }

  case object HalfElfDilettanteDruid
      extends RacialFeat
      with HalfElfDilettanteDruid {
    override protected def nameSource: String =
      CharacterClass.Druid.entryName.toPascalCase
  }

  case object HalfElfDilettanteFavoredSoul
      extends RacialFeat
      with HalfElfDilettanteFavoredSoul {
    override protected def nameSource: String =
      CharacterClass.FavoredSoul.entryName.splitByCase.toPascalCase
  }

  case object HalfElfDilettanteFighter
      extends RacialFeat
      with HalfElfDilettanteFighter {
    override protected def nameSource: String =
      CharacterClass.Fighter.entryName.toPascalCase
  }

  case object HalfElfDilettantePaladin
      extends RacialFeat
      with HalfElfDilettantePaladin {
    override protected def nameSource: String =
      CharacterClass.Paladin.entryName.toPascalCase
  }

  case object HalfElfDilettanteRanger
      extends RacialFeat
      with HalfElfDilettanteRanger {
    override protected def nameSource: String =
      CharacterClass.Ranger.entryName.toPascalCase
  }

  case object HalfElfDilettanteRogue
      extends RacialFeat
      with HalfElfDilettanteRogue {
    override protected def nameSource: String =
      CharacterClass.Rogue.entryName.toPascalCase
  }

  case object HalfElfDilettanteSorcerer
      extends RacialFeat
      with HalfElfDilettanteSorcerer {
    override protected def nameSource: String =
      CharacterClass.Sorcerer.entryName.toPascalCase
  }

  case object HalfElfDilettanteWarlock
      extends RacialFeat
      with HalfElfDilettanteWarlock {
    override protected def nameSource: String =
      CharacterClass.Warlock.entryName.toPascalCase
  }

  case object HalfElfDilettanteWizard
      extends RacialFeat
      with HalfElfDilettanteWizard {
    override protected def nameSource: String =
      CharacterClass.Wizard.entryName.toPascalCase
  }

  override def values: Seq[RacialFeat] = findValues
}
