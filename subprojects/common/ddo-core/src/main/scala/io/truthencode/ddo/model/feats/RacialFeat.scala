/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: RacialFeat.scala
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.truthencode.ddo.model.feats

import com.typesafe.scalalogging.LazyLogging
import enumeratum.Enum
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.effect.features.{Features, FeaturesImpl}
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.FriendlyDisplay
import io.truthencode.ddo.support.requisite.{Inclusion, RaceRequisiteImpl, Requisite, RequisiteType}

import scala.collection.immutable.IndexedSeq

/**
 * Created by adarr on 2/14/2017.
 */
sealed trait RacialFeat
  extends Feat with RaceRequisiteImpl with FriendlyDisplay with FeatMatcher with LazyLogging
  with FeaturesImpl {
  self: FeatType & Requisite & Inclusion & RequisiteType & Features =>
  val matchFeat: PartialFunction[Feat, RacialFeat] = { case x: RacialFeat =>
    x
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

  override def values: IndexedSeq[RacialFeat] = findValues

  // Racial Feats
  // Warforged
  case object CompositePlating extends RacialFeat with CompositePlating

  case object LightFortification extends RacialFeat with LightFortification

  case object WarforgedTraits extends RacialFeat with WarforgedTraits

  case object AdamantineBody extends RacialFeat with AdamantineBody

  case object MithralBody extends RacialFeat with MithralBody

  case object ImprovedDamageReduction extends RacialFeat with ImprovedDamageReduction

  case object ImprovedFortification extends RacialFeat with ImprovedFortification

  case object MithralFluidity extends RacialFeat with MithralFluidity

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

  case object HalfElfMixedHeritage extends RacialFeat with HalfElfMixedHeritage {
    override protected def nameSource: String = "Mixed Heritage".toPascalCase
  }

  case object HalfElfSocialGraces extends RacialFeat with HalfElfSocialGraces {
    override protected def nameSource: String = "Social Graces".toPascalCase
  }

  case object ImmunityToSleep extends RacialFeat with ImmunityToSleep

  case object HalfElfDilettanteMonk extends RacialFeat with HalfElfDilettanteMonk {
    override protected def nameSource: String =
      HeroicCharacterClass.Monk.entryName.toPascalCase
  }

  case object HalfElfDilettanteArtificer extends RacialFeat with HalfElfDilettanteArtificer {
    override protected def nameSource: String =
      HeroicCharacterClass.Artificer.entryName.toPascalCase
  }

  case object HalfElfDilettanteBarbarian extends RacialFeat with HalfElfDilettanteBarbarian {
    override protected def nameSource: String =
      HeroicCharacterClass.Barbarian.entryName.toPascalCase
  }

  case object HalfElfDilettanteBard extends RacialFeat with HalfElfDilettanteBard {
    override protected def nameSource: String =
      HeroicCharacterClass.Bard.entryName.toPascalCase
  }

  case object HalfElfDilettanteCleric extends RacialFeat with HalfElfDilettanteCleric {
    override protected def nameSource: String =
      HeroicCharacterClass.Cleric.entryName.toPascalCase
  }

  case object HalfElfDilettanteDruid extends RacialFeat with HalfElfDilettanteDruid {
    override protected def nameSource: String =
      HeroicCharacterClass.Druid.entryName.toPascalCase
  }

  case object HalfElfDilettanteFavoredSoul extends RacialFeat with HalfElfDilettanteFavoredSoul {
    override protected def nameSource: String =
      HeroicCharacterClass.FavoredSoul.entryName.splitByCase.toPascalCase
  }

  case object HalfElfDilettanteFighter extends RacialFeat with HalfElfDilettanteFighter {
    override protected def nameSource: String =
      HeroicCharacterClass.Fighter.entryName.toPascalCase
  }

  case object HalfElfDilettantePaladin extends RacialFeat with HalfElfDilettantePaladin {
    override protected def nameSource: String =
      HeroicCharacterClass.Paladin.entryName.toPascalCase
  }

  case object HalfElfDilettanteRanger extends RacialFeat with HalfElfDilettanteRanger {
    override protected def nameSource: String =
      HeroicCharacterClass.Ranger.entryName.toPascalCase
  }

  case object HalfElfDilettanteRogue extends RacialFeat with HalfElfDilettanteRogue {
    override protected def nameSource: String =
      HeroicCharacterClass.Rogue.entryName.toPascalCase
  }

  case object HalfElfDilettanteSorcerer extends RacialFeat with HalfElfDilettanteSorcerer {
    override protected def nameSource: String =
      HeroicCharacterClass.Sorcerer.entryName.toPascalCase
  }

  case object HalfElfDilettanteWarlock extends RacialFeat with HalfElfDilettanteWarlock {
    override protected def nameSource: String =
      HeroicCharacterClass.Warlock.entryName.toPascalCase
  }

  case object HalfElfDilettanteWizard extends RacialFeat with HalfElfDilettanteWizard {
    override protected def nameSource: String =
      HeroicCharacterClass.Wizard.entryName.toPascalCase
  }
}
