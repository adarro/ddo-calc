/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: EpicFeat.scala
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

import enumeratum.Enum
import io.truthencode.ddo.model.effect.features.{Features, FeaturesImpl}
import io.truthencode.ddo.model.schools.School
import io.truthencode.ddo.support.EpicLevels
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{FriendlyDisplay, Prefix}
import io.truthencode.ddo.support.requisite._

import scala.collection.immutable

/**
 * Created by adarr on 2/14/2017.
 */
sealed trait EpicFeat
  extends Feat with FriendlyDisplay with SubFeatInformation with LevelRequisiteImpl
  with RequiresCharacterLevel with FeaturesImpl {
  self: FeatType & Requisite & Inclusion & EpicFeatCategory & Features =>

  /**
   * Default Minimum Level for all Epic Feats. Override this with a higher level as needed.
   */
  override val requireCharacterLevel: Int = EpicLevels.min

}

// scalastyle:off  number.of.methods
object EpicFeat extends Enum[EpicFeat] with FeatSearchPrefix with FeatMatcher {
  val matchFeat: PartialFunction[Feat, EpicFeat] = { case x: EpicFeat =>
    x
  }
  val matchFeatById: PartialFunction[String, EpicFeat] = {
    case x: String if EpicFeat.namesToValuesMap.contains(x) =>
      EpicFeat.withNameOption(x) match {
        case Some(y) => y
      }
  }

  def epicSpellFocusAny: immutable.IndexedSeq[EpicSpellFocus] =
    for x <- School.values yield EpicSpellFocus(x)

  override def values: immutable.IndexedSeq[EpicFeat] = findValues

  case class EpicSpellFocus(school: School)
    extends EpicSpellFocusBase with EpicFeat with SubFeat with Prefix {

    /**
     * Delimits the prefix and text.
     */
    override protected val prefixSeparator: String = ": "

    override def prefix: Option[String] = Some("EpicSpellFocus".splitByCase)

    override def allOfFeats: Seq[GeneralFeat] = lesser

    private def lesser = GeneralFeat.spellFocusAny.filter { x =>
      x.school.eq(school)
    }

    override protected def nameSource: String = school.displayText
  }

  // General Passive Feats
  case object BlindingSpeed
    extends FeatRequisiteImpl with EpicFeat with GeneralPassive with FreeFeat with BlindingSpeed

  case object BulwarkOfDefense
    extends FeatRequisiteImpl with EpicFeat with GeneralPassive with FreeFeat with BulwarkOfDefense

  case object EpicDamageReduction
    extends FeatRequisiteImpl with EpicFeat with GeneralPassive with FreeFeat
    with EpicDamageReduction

  case object EpicFortitude
    extends FeatRequisiteImpl with EpicFeat with GeneralPassive with FreeFeat with EpicFortitude

  case object EpicReflexes
    extends FeatRequisiteImpl with EpicFeat with GeneralPassive with FreeFeat with EpicReflexes

  case object EpicReputation
    extends FeatRequisiteImpl with EpicFeat with GeneralPassive with FreeFeat with EpicReputation

  case object EpicSkills
    extends FeatRequisiteImpl with EpicFeat with GeneralPassive with FreeFeat with EpicSkills

  case object EpicWill
    extends FeatRequisiteImpl with EpicFeat with GeneralPassive with FreeFeat with EpicWill

  case object GreatAbility
    extends FeatRequisiteImpl with EpicFeat with GeneralPassive with FreeFeat with GreatAbility

  case object OverwhelmingCritical
    extends FeatRequisiteImpl with EpicFeat with GeneralPassive with FreeFeat
    with OverwhelmingCritical

  case object EpicToughness
    extends FeatRequisiteImpl with EpicFeat with GeneralPassive with FreeFeat with EpicToughness

  case object WatchfulEye
    extends FeatRequisiteImpl with EpicFeat with GeneralPassive with FreeFeat with WatchfulEye

  // Ranged Combat Passive
  case object CombatArchery
    extends FeatRequisiteImpl with EpicFeat with RangedCombatPassive with CombatArchery

  case object BurstOfGlacialWrath extends FeatRequisiteImpl with EpicFeat with BurstOfGlacialWrath

  case object Ruin extends FeatRequisiteImpl with EpicFeat with Ruin

  case object GreaterRuin extends FeatRequisiteImpl with EpicFeat with GreaterRuin

  case object EpicMentalToughness extends FeatRequisiteImpl with EpicFeat with EpicMentalToughness

  case object EpicSpellFocus extends EpicSpellFocusBase with EpicFeat with ParentFeat {
    override val subFeats: Seq[EpicFeat & SubFeat] =
      epicSpellFocusAny
  }

  case object EpicSpellPenetration extends FeatRequisiteImpl with EpicFeat with EpicSpellPenetration

  case object ImprovedAugmentSummoning
    extends FeatRequisiteImpl with EpicFeat with ImprovedAugmentSummoning

  case object MasterOfAir
    extends FeatRequisiteImpl with EpicFeat with SpellCastingPassive with FreeFeat with MasterOfAir

  case object MasterOfAlignment
    extends FeatRequisiteImpl with EpicFeat with SpellCastingPassive with FreeFeat
    with MasterOfAlignment

  case object MasterOfArtifice
    extends FeatRequisiteImpl with EpicFeat with SpellCastingPassive with FreeFeat
    with MasterOfArtifice

  case object MasterOfEarth
    extends FeatRequisiteImpl with EpicFeat with SpellCastingPassive with FreeFeat
    with MasterOfEarth

  case object MasterOfFire
    extends FeatRequisiteImpl with EpicFeat with SpellCastingPassive with FreeFeat with MasterOfFire

  case object MasterOfKnowledge
    extends FeatRequisiteImpl with EpicFeat with SpellCastingPassive with FreeFeat
    with MasterOfKnowledge

  case object MasterOfLight
    extends FeatRequisiteImpl with EpicFeat with SpellCastingPassive with FreeFeat
    with MasterOfLight

  case object MasterOfMusic
    extends FeatRequisiteImpl with EpicFeat with SpellCastingPassive with FreeFeat
    with MasterOfMusic

  case object MasterOfWater
    extends FeatRequisiteImpl with EpicFeat with SpellCastingPassive with FreeFeat
    with MasterOfWater

  case object MasterOfTheDead
    extends FeatRequisiteImpl with EpicFeat with SpellCastingPassive with FreeFeat
    with MasterOfTheDead

  case object MasterOfTheWilds
    extends FeatRequisiteImpl with EpicFeat with SpellCastingPassive with FreeFeat
    with MasterOfTheWilds

  case object EmboldenSpell extends FeatRequisiteImpl with EpicFeat with FreeFeat with EmboldenSpell

  case object IntensifySpell
    extends FeatRequisiteImpl with EpicFeat with FreeFeat with IntensifySpell

  case object ConstructExemplar
    extends FeatRequisiteImpl with RaceRequisiteImpl with ClassRequisiteImpl with EpicFeat
    with FreeFeat with ConstructExemplar

  case object InspireExcellence
    extends FeatRequisiteImpl with SkillRequisiteImpl with ClassRequisiteImpl with EpicFeat
    with InspireExcellence

  case object ImprovedMartialArts extends FeatRequisiteImpl with EpicFeat with ImprovedMartialArts

  case object VorpalStrikes extends VorpalStrikes with EpicFeat

  case object ImprovedSneakAttack extends ImprovedSneakAttack with EpicFeat

  case object EpicEldritchBlast extends EpicEldritchBlast with EpicFeat
}
