/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ClassEnhancement.scala
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
package io.truthencode.ddo.model.enhancement.enhancements

import enumeratum.Enum
import io.truthencode.ddo.model.enhancement.{ClassBased, ClassBasedEnhancements, Enhancement, Tier}
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.requisite.{
  PointInTreeRequisite,
  PointsAvailableRequisite,
  RequiresActionPoints
}
import io.truthencode.ddo.support.tree.{ClassTrees, Ranks}

import scala.collection.immutable

/**
 * Class based enhancement which requires at least one level in a particular class.
 */
sealed trait ClassEnhancement extends Enhancement with ClassBased with Ranks {
  self: Tier & ClassBasedEnhancements & PointInTreeRequisite & PointsAvailableRequisite &
    RequiresActionPoints =>
  val tree: ClassTrees

  override def displayText: String = displaySource.lowerCaseNoise
}

protected trait ClassEnhancementImpl extends ClassEnhancement {
  self: Tier & ClassBasedEnhancements & PointInTreeRequisite & PointsAvailableRequisite &
    RequiresActionPoints =>
}

// scalastyle:off number.of.methods
object ClassEnhancement extends Enum[ClassEnhancement] with ClassEnhancementSearchPrefix {

  override def values: immutable.IndexedSeq[ClassEnhancement] =
    findValues ++ generateObliterationMultiSelector ++ generateWeakeningMixtureMultiSelector

  // Generator for Obliteration selectors, would like to do this in a less horrible manner at some point
  protected def generateObliterationMultiSelector: Seq[ElementalObliterationSelector] = {
    Seq(
      ("Fiery", "Fire"),
      ("Frost", "Cold"),
      ("Caustic", "Acid"),
      ("Poison", "Poison"),
      ("Voltaic", "Electric")
    ).map { p =>
      ElementalObliterationSelector(p._1, p._2)
    }

  }

  protected def generateWeakeningMixtureMultiSelector: Seq[WeakeningMixtureSelector] = {
    Seq("Fire", "Cold", "Electric", "Acid", "Poison").map { p =>
      WeakeningMixtureSelector(p, p)
    }
  }

  case class ElementalObliterationSelector(id: String, element: String)
    extends ClassEnhancement, ObliterationMultiSelector {
    override def entryName: String = nameSource

    override protected def nameSource: String = s"${id}Obliteration"
  }

  // Weakening Mixture multi select for element
  case class WeakeningMixtureSelector(id: String, element: String)
    extends ClassEnhancement, WeakeningMixtureMultiSelector {
    override def entryName: String = withPrefix.getOrElse("").concat(id)

    override protected def nameSource: String = id
  }

  // Alchemist

  // Vile Chemist
  // Core Enhancements
  case object Poisoner extends ClassEnhancement, Poisoner

  case object PoisonedCoating extends ClassEnhancement, PoisonedCoating

  case object HiddenBladesI extends ClassEnhancement, HiddenBladesI

  case object HiddenBladesII extends ClassEnhancement, HiddenBladesII

  case object HiddenBladesIII extends ClassEnhancement, HiddenBladesIII

  case object VenomsGrip extends ClassEnhancement, VenomsGrip

  // Apothecary
  // Core Enhancements
  case object Determination extends ClassEnhancement, Determination

  case object AlchemicalShield extends ClassEnhancement, AlchemicalShield

  case object CurativeAdmixtureCureSeriousWounds
    extends ClassEnhancement, CurativeAdmixtureCureSeriousWounds

  case object SpillTheBadStuff extends ClassEnhancement, SpillTheBadStuff

  case object CurativeAdmixtureHeal extends ClassEnhancement, CurativeAdmixtureHeal

  case object GeniusNeverDies extends ClassEnhancement, GeniusNeverDies

  // Tier1
  case object CurativeAdmixtureCureLightWounds
    extends ClassEnhancement, CurativeAdmixtureCureLightWounds

  case object ApothecarySkills extends ClassEnhancement, ApothecarySkills

  case object SpellCriticalChancePositiveAndNegativeI
    extends ClassEnhancement, SpellCriticalChancePositiveAndNegativeI

  case object EnergyOfTheScholar extends ClassEnhancement, EnergyOfTheScholar

  case object SoothingPoultices extends ClassEnhancement, SoothingPoultices

  // Tier2
  case object HaleAndHearty extends ClassEnhancement, HaleAndHearty

  case object SpellCriticalChancePositiveAndNegativeII
    extends ClassEnhancement, SpellCriticalChancePositiveAndNegativeII

  case object StoneOfTheScholar extends ClassEnhancement, StoneOfTheScholar

  case object LifeSalve extends ClassEnhancement, LifeSalve

  // Tier3
  case object PanaceaPoultice extends ClassEnhancement, PanaceaPoultice

  case object SafetyGoggles extends ClassEnhancement, SafetyGoggles

  case object SpellCriticalChancePositiveAndNegativeIII
    extends ClassEnhancement, SpellCriticalChancePositiveAndNegativeIII

  case object WillfulAmbition extends ClassEnhancement, WillfulAmbition

  case object ApothecaryAbilityI extends ClassEnhancement, ApothecaryAbilityI

  // Tier4
  case object InsulatedBoots extends ClassEnhancement, InsulatedBoots

  case object SpellCriticalChancePositiveAndNegativeIV
    extends ClassEnhancement, SpellCriticalChancePositiveAndNegativeIV

  case object RunForYourLife extends ClassEnhancement, RunForYourLife

  case object AbilityII extends ClassEnhancement, ApothecaryAbilityII

  // Tier5
  case object CurativeAdmixtureCureOrInflictCriticalWounds
    extends ClassEnhancement, CurativeAdmixtureCureOrInflictCriticalWounds

  case object GlovesOfTheMasterApothecary extends ClassEnhancement, GlovesOfTheMasterApothecary

  case object MasterApothecary extends ClassEnhancement, MasterApothecary

  case object Dissolve extends ClassEnhancement, Dissolve

  case object DeadlyNeurotoxinNightshade extends ClassEnhancement, DeadlyNeurotoxinNightshade

  // Bombardier
  // Core
  case object AlchemicalResistance extends ClassEnhancement, AlchemicalResistance

  case object ArcaneOil extends ClassEnhancement, ArcaneOil

  case object LiquidPowerI extends ClassEnhancement, LiquidPowerI

  case object LiquidPowerII extends ClassEnhancement, LiquidPowerII

  case object LiquidPowerIII extends ClassEnhancement, LiquidPowerIII

  case object Multivial extends ClassEnhancement, Multivial

  // Tier1
  case object SpellvialSelection extends ClassEnhancement, SpellvialSelection

  case object ArcaneKnowledge extends ClassEnhancement, ArcaneKnowledge

  case object MagicalSubtlety extends ClassEnhancement, MagicalSubtlety

  case object SpellCriticalElementalAndPoisonI
    extends ClassEnhancement, SpellCriticalElementalAndPoisonI

  case object WandAndScrollMastery extends ClassEnhancement, WandAndScrollMastery

  // Tier2
  case object RapidCondensation extends ClassEnhancement, SLARapidCondensation

  case object EfficientMetamagicsI extends ClassEnhancement, EfficientMetamagicsI

  case object StoneOfTheSavant extends ClassEnhancement, StoneOfTheSavant

  case object SpellCriticalElementalAndPoisonII
    extends ClassEnhancement, SpellCriticalElementalAndPoisonII

  case object ElementalDefenses extends ClassEnhancement, ElementalDefenses

  // Tier3
  case object EfficientMetamagicsII extends ClassEnhancement, EfficientMetamagicsII

  case object SwiftAmbition extends ClassEnhancement, SwiftAmbition

  case object SpellCriticalElementalAndPoisonIII
    extends ClassEnhancement, SpellCriticalElementalAndPoisonIII

  case object AbilityIBombardier extends ClassEnhancement, AbilityIBombardier

  // Tier4
  case object SmokeBomb extends ClassEnhancement, SLASmokeBomb

  // Tier5

  case object EfficientHeighten extends ClassEnhancement, EfficientHeighten

  case object BurningAmbition extends ClassEnhancement, BurningAmbition
  // Parent needs SLA for each element and a means to express it

  case object SpellCriticalElementalAndPoisonIV
    extends ClassEnhancement, SpellCriticalElementalAndPoisonIV

  case object BomAbilityII extends ClassEnhancement, BomAbilityII

  case object ElementalObliteration extends ClassEnhancement, ElementalObliteration {

    override val subEnhancements: Seq[ClassEnhancement & SubEnhancement] =
      generateObliterationMultiSelector
  }

  case object Augmentation extends ClassEnhancement, Augmentation

  case object InfernoOfCreation extends ClassEnhancement, InfernoOfCreation

  case object ConjurationFocus extends ClassEnhancement, ConjurationFocus

  case object WeakeningMixture extends ClassEnhancement, WeakeningMixture {

    override val subEnhancements: Seq[ClassEnhancement & SubEnhancement] =
      generateWeakeningMixtureMultiSelector
  }
}
