/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
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
  self: Tier
    with ClassBasedEnhancements with PointInTreeRequisite with PointsAvailableRequisite
    with RequiresActionPoints =>
  val tree: ClassTrees

  override def displayText: String = displaySource.lowerCaseNoise
}

protected trait ClassEnhancementImpl extends ClassEnhancement {
  self: Tier
    with ClassBasedEnhancements with PointInTreeRequisite with PointsAvailableRequisite
    with RequiresActionPoints =>
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
    extends ObliterationMultiSelector {
    override def entryName: String = nameSource

    override protected def nameSource: String = s"${id}Obliteration"
  }

  // Weakening Mixture multi select for element
  case class WeakeningMixtureSelector(id: String, element: String)
    extends WeakeningMixtureMultiSelector {
    override def entryName: String = withPrefix.getOrElse("").concat(id)

    override protected def nameSource: String = id
  }

  // Alchemist
  // Apothecary
  // Core Enhancements
  case object Determination extends Determination

  case object AlchemicalShield extends AlchemicalShield

  case object CurativeAdmixtureCureSeriousWounds extends CurativeAdmixtureCureSeriousWounds

  case object SpillTheBadStuff extends SpillTheBadStuff

  case object CurativeAdmixtureHeal extends CurativeAdmixtureHeal

  case object GeniusNeverDies extends GeniusNeverDies

  // Tier1
  case object CurativeAdmixtureCureLightWounds extends CurativeAdmixtureCureLightWounds

  case object ApothecarySkills extends ApothecarySkills

  case object SpellCriticalChancePositiveAndNegativeI
    extends SpellCriticalChancePositiveAndNegativeI

  case object EnergyOfTheScholar extends EnergyOfTheScholar

  case object SoothingPoultices extends SoothingPoultices

  // Tier2
  case object HaleAndHearty extends HaleAndHearty

  case object SpellCriticalChancePositiveAndNegativeII
    extends SpellCriticalChancePositiveAndNegativeII

  case object StoneOfTheScholar extends StoneOfTheScholar

  case object LifeSalve extends LifeSalve

  // Tier3
  case object PanaceaPoultice extends PanaceaPoultice

  case object SafetyGoggles extends SafetyGoggles

  case object SpellCriticalChancePositiveAndNegativeIII
    extends SpellCriticalChancePositiveAndNegativeIII

  case object WillfulAmbition extends WillfulAmbition

  case object ApothecaryAbilityI extends ApothecaryAbilityI

  // Tier4
  case object InsulatedBoots extends InsulatedBoots

  case object SpellCriticalChancePositiveAndNegativeIV
    extends SpellCriticalChancePositiveAndNegativeIV

  case object RunForYourLife extends RunForYourLife

  case object AbilityII extends ApothecaryAbilityII

  // Tier5
  case object CurativeAdmixtureCureOrInflictCriticalWounds
    extends CurativeAdmixtureCureOrInflictCriticalWounds

  case object GlovesOfTheMasterApothecary extends GlovesOfTheMasterApothecary

  case object MasterApothecary extends MasterApothecary

  case object Dissolve extends Dissolve

  case object DeadlyNeurotoxinNightshade extends DeadlyNeurotoxinNightshade

  // Bombardier
  // Core
  case object AlchemicalResistance extends AlchemicalResistance

  case object ArcaneOil extends ArcaneOil

  case object LiquidPowerI extends LiquidPowerI

  case object LiquidPowerII extends LiquidPowerII

  case object LiquidPowerIII extends LiquidPowerIII

  case object Multivial extends Multivial

  // Tier1
  case object SpellvialSelection extends SpellvialSelection

  case object ArcaneKnowledge extends ArcaneKnowledge

  case object MagicalSubtlety extends MagicalSubtlety

  case object SpellCriticalElementalAndPoisonI extends SpellCriticalElementalAndPoisonI

  case object WandAndScrollMastery extends WandAndScrollMastery

  // Tier2
  case object RapidCondensation extends SLARapidCondensation

  case object EfficientMetamagicsI extends EfficientMetamagicsI

  case object StoneOfTheSavant extends StoneOfTheSavant

  case object SpellCriticalElementalAndPoisonII extends SpellCriticalElementalAndPoisonII

  case object ElementalDefenses extends ElementalDefenses

  // Tier3
  case object EfficientMetamagicsII extends EfficientMetamagicsII

  case object SwiftAmbition extends SwiftAmbition

  case object SpellCriticalElementalAndPoisonIII extends SpellCriticalElementalAndPoisonIII

  case object AbilityIBombardier extends AbilityIBombardier

  // Tier4
  case object SmokeBomb extends SLASmokeBomb

  // Tier5

  case object EfficientHeighten extends EfficientHeighten

  case object BurningAmbition extends BurningAmbition
  // Parent needs SLA for each element and a means to express it

  case object SpellCriticalElementalAndPoisonIV extends SpellCriticalElementalAndPoisonIV

  case object BomAbilityII extends BomAbilityII

  case object ElementalObliteration extends ElementalObliteration {

    override val subEnhancements: Seq[ClassEnhancement with SubEnhancement] =
      generateObliterationMultiSelector
  }

  case object Augmentation extends Augmentation

  case object InfernoOfCreation extends InfernoOfCreation

  case object ConjurationFocus extends ConjurationFocus

  case object WeakeningMixture extends WeakeningMixture {

    override val subEnhancements: Seq[ClassEnhancement with SubEnhancement] =
      generateWeakeningMixtureMultiSelector
  }
}
