/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: DragonmarkFeat.scala
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
import io.truthencode.ddo.activation.{OnSpellLikeAbilityEvent, TriggeredActivationImpl}
import io.truthencode.ddo.model.UnknownDuration
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{Features, FeaturesImpl}
import io.truthencode.ddo.model.race.Race
import io.truthencode.ddo.model.race.Race.{Dwarf, Elf, Gnome, HalfElf, HalfOrc, Halfling, Human}
import io.truthencode.ddo.support.naming.FriendlyDisplay
import io.truthencode.ddo.support.requisite.*

import java.time.Duration
import scala.collection.immutable

/**
 * Created by adarr on 3/26/2017.
 */
sealed trait DragonmarkFeat
  extends Feat with TriggeredActivationImpl with FriendlyDisplay with SubFeatInformation
  with FeatMatcher with FeatRequisiteImpl with FeaturesImpl {
  self: FeatType & Requisite & RequisiteType & Inclusion & Features & TriggerEvent =>

  val matchFeat: PartialFunction[Feat, DragonmarkFeat] = { case x: DragonmarkFeat =>
    x
  }
  val matchFeatById: PartialFunction[String, DragonmarkFeat] = {
    case x: String if DragonmarkFeat.namesToValuesMap.contains(x) =>
      DragonmarkFeat.withNameOption(x) match {
        case Some(y) => y
      }
  }
}

/**
 * [[https://ddowiki.com/page/Dragonmark Dragon Mark]] Feats
 * @note
 *   Lesser and Greater Dragonmarks are now Tier 2 and 3 Racial Enhancements and need to be coded as
 *   such
 */
object DragonmarkFeat extends Enum[DragonmarkFeat] with FeatSearchPrefix {

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified
   * "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "Dragonmark: "

//  /**
//    *  [[https://ddowiki.com/page/Dragonmark_of_Storm]]
//    *  Activate this ability to harness the bloodline of House Lyrandar to cast Electric Loop two times per rest.
//    *  Also allows you to use your Least Mark of Storm an extra time per rest.
//    *  Electric Loop strikes multiple targets with electricity, sometimes dazing them.
//    */
//  case object LesserDragonmarkOfStorm
//      extends DragonmarkFeat
//      with RaceRequisiteImpl
//      with RequiresAllOfRace
//      with ActiveFeat
//      with OnSpellLikeAbilityEvent {
//    override def allOfRace: Seq[(Race, Int)] = List((HalfElf, 1))
//    override def coolDown: Option[Duration] = Some(UnknownDuration)
//  }

//  /**
//    *  [[https://ddowiki.com/page/Dragonmark_of_Storm]]
//    *  Activate this ability to harness the bloodline of House Lyrandar to cast Electric Loop two times per rest.
//    *  Also allows you to use your Least Mark of Storm an extra time per rest.
//    *  Electric Loop strikes multiple targets with electricity, sometimes dazing them.
//    */
//  case object GreaterDragonmarkOfStorm
//      extends DragonmarkFeat
//      with RaceRequisiteImpl
//      with RequiresAllOfRace
//      with ActiveFeat
//      with OnSpellLikeAbilityEvent {
//    override def allOfRace: Seq[(Race, Int)] = List((HalfElf, 1))
//    override def coolDown: Option[Duration] = Some(UnknownDuration)
//  }

  override def values: immutable.IndexedSeq[DragonmarkFeat] = findValues

  /**
   * [[https://ddowiki.com/page/Dragonmark_of_Storm]] The bloodline of House Lyrandar bestows on you
   * a +2 bonus to your Balance skill. Activate this ability to cast Gust of Wind three times per
   * rest. Gust of Wind creates a severe blast of wind that slows enemy movement and disperses
   * clouds.
   */
  case object LeastDragonmarkOfStorm
    extends DragonmarkFeat with TriggeredActivationImpl with RaceRequisiteImpl
    with RequiresAllOfRace with ActiveFeat with OnSpellLikeAbilityEvent with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((HalfElf, 1))

    override def coolDown: Option[Duration] = Some(UnknownDuration)
  }

//  /**
//    * [[https://ddowiki.com/page/Least_Dragonmark_of_Shadow Least Dragonmark of Shadow]]
//    * The bloodline of House Phiarlan bestows on you a +2 bonus to your Hide skill.
//    * Activate this ability to cast Invisibility three times per rest.
//    */
//  case object LesserDragonmarkOfFinding
//      extends DragonmarkFeat
//      with RaceRequisiteImpl
//      with RequiresAllOfRace
//      with ActiveFeat
//      with OnSpellLikeAbilityEvent
//      with Passive {
//    override def allOfRace: Seq[(Race, Int)] = List((HalfOrc, 1))
//
//    override def coolDown: Option[Duration] = Some(UnknownDuration)
//  }

//  /**
//    * [[https://ddowiki.com/page/Least_Dragonmark_of_Shadow Least Dragonmark of Shadow]]
//    * The bloodline of House Phiarlan bestows on you a +2 bonus to your Hide skill.
//    * Activate this ability to cast Invisibility three times per rest.
//    */
//  case object GreaterDragonmarkOfFinding
//      extends DragonmarkFeat
//      with RaceRequisiteImpl
//      with RequiresAllOfRace
//      with ActiveFeat
//      with OnSpellLikeAbilityEvent
//      with Passive {
//    override def allOfRace: Seq[(Race, Int)] = List((HalfOrc, 1))
//
//    override def coolDown: Option[Duration] = Some(UnknownDuration)
//  }

  /**
   * [[https://ddowiki.com/page/Dragonmark_of_Storm]] Activate this ability to harness the bloodline
   * of House Lyrandar to cast Call Lightning Storm once per rest. Also allows you to use your Least
   * and Lesser Marks of Storm an extra time per rest. Call Lightning Storm calls down lightning
   * bolts from the sky.
   */
  case object LeastDragonmarkOfShadow
    extends DragonmarkFeat with RaceRequisiteImpl with RequiresAllOfRace with ActiveFeat
    with OnSpellLikeAbilityEvent with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((Elf, 1))

    override def coolDown: Option[Duration] = Some(UnknownDuration)
  }

//  case object LesserDragonmarkOfHealing
//      extends DragonmarkFeat
//      with RaceRequisiteImpl
//      with RequiresAnyOfRace
//      with RequiresAllOfFeat
//      with ActiveFeat
//      with OnSpellLikeAbilityEvent
//      with Passive {
//    override def coolDown: Option[Duration] = Some(Duration.ofSeconds(1))
//
//    override def anyOfRace: Seq[(Race, Int)] = List((Halfling, 1), (Human, 1))
//
//    override def allOfFeats: Seq[Feat] = Seq(LeastDragonmarkOfHealing)
//  }

//  case object GreaterDragonmarkOfHealing
//      extends DragonmarkFeat
//      with RaceRequisiteImpl
//      with RequiresAnyOfRace
//      with RequiresAllOfFeat
//      with ActiveFeat
//      with OnSpellLikeAbilityEvent
//      with Passive {
//    override def coolDown: Option[Duration] = Some(Duration.ofSeconds(1))
//
//    override def allOfFeats: Seq[Feat] =
//      Seq(LesserDragonmarkOfHealing, LeastDragonmarkOfHealing)
//    override def anyOfRace: Seq[(Race, Int)] = List((Halfling, 1), (Human, 1))
//  }

  /**
   * [[https://ddowiki.com/page/Least_Dragonmark_of_Shadow Least Dragonmark of Shadow]] The
   * bloodline of House Phiarlan bestows on you a +2 bonus to your Hide skill. Activate this ability
   * to cast Invisibility three times per rest.
   */
  case object LeastDragonmarkOfFinding
    extends DragonmarkFeat with RaceRequisiteImpl with RequiresAllOfRace with ActiveFeat
    with OnSpellLikeAbilityEvent with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((HalfOrc, 1))

    override def coolDown: Option[Duration] = Some(UnknownDuration)
  }

//  /**
//    * [[https://ddowiki.com/page/Lesser_Dragonmark_of_Making Lesser Dragonmark of Making]]
//    * Activate this ability to cast Repair Serious Damage.
//    * The Dragonmarks of Making also grant bonuses (+3 for Least, +3 for Lesser, and +4 for Greater) to crafting skills.
//    */
//  case object LesserDragonmarkOfMaking
//      extends DragonmarkFeat
//      with RaceRequisiteImpl
//      with RequiresAnyOfRace
//      with RequiresAllOfFeat
//      with ActiveFeat
//      with OnSpellLikeAbilityEvent {
//    override def coolDown: Option[Duration] = Some(UnknownDuration)
//
//    override def anyOfRace: Seq[(Race, Int)] = List((Human, 1))
//
//    override def allOfFeats: Seq[Feat] = Seq(LeastDragonmarkOfMaking)
//  }

//  /**
//    * [[https://ddowiki.com/page/Greater_Dragonmark_of_Making Greater Dragonmark of Making]]
//    * Activate this ability to cast Reconstruct.
//    * The Dragonmarks of Making also grant bonuses (+3 for Least, +3 for Lesser, and +4 for Greater) to crafting skills.
//    */
//  case object GreaterDragonmarkOfMaking
//      extends DragonmarkFeat
//      with RaceRequisiteImpl
//      with RequiresAnyOfRace
//      with RequiresAllOfFeat
//      with ActiveFeat
//      with OnSpellLikeAbilityEvent {
//    override def coolDown: Option[Duration] = Some(UnknownDuration)
//
//    override def allOfFeats: Seq[Feat] = Seq(LesserDragonmarkOfMaking)
//    override def anyOfRace: Seq[(Race, Int)] = List((Human, 1))
//  }

  case object LeastDragonmarkOfHealing
    extends DragonmarkFeat with RaceRequisiteImpl with RequiresAnyOfRace with ActiveFeat
    with OnSpellLikeAbilityEvent with Passive {
    override def coolDown: Option[Duration] = Some(Duration.ofSeconds(1))

    override def anyOfRace: Seq[(Race, Int)] = List((Halfling, 1), (Human, 1))
  }

//  /**
//    * Undocumented
//    */
//  case object LesserDragonmarkOfPassage
//      extends DragonmarkFeat
//      with RaceRequisiteImpl
//      with RequiresAllOfRace
//      with ActiveFeat
//      with OnSpellLikeAbilityEvent {
//    override def allOfRace: Seq[(Race, Int)] = List((Human, 1))
//
//    override def coolDown: Option[Duration] = Some(UnknownDuration)
//  }

//  /**
//    * Undocumented
//    */
//  case object GreaterDragonmarkOfPassage
//      extends DragonmarkFeat
//      with RaceRequisiteImpl
//      with RequiresAllOfRace
//      with ActiveFeat
//      with OnSpellLikeAbilityEvent {
//    override def allOfRace: Seq[(Race, Int)] = List((Human, 1))
//
//    override def coolDown: Option[Duration] = Some(UnknownDuration)
//  }

  /**
   * [[https://ddowiki.com/page/Least_Dragonmark_of_Making Least Dragonmark of Making]] The
   * bloodline of House Cannith bestows on you a +2 bonus to your Repair skill. Activate this
   * ability to cast Repair Light Damage. The Dragonmarks of Making also grant bonuses (+3 for
   * Least, +3 for Lesser, and +4 for Greater) to crafting skills.
   */
  case object LeastDragonmarkOfMaking
    extends DragonmarkFeat with RaceRequisiteImpl with RequiresAnyOfRace with ActiveFeat
    with OnSpellLikeAbilityEvent with Passive {
    override def coolDown: Option[Duration] = Some(UnknownDuration)

    override def anyOfRace: Seq[(Race, Int)] = List((Human, 1))
  }

//  /**
//    * [[https://ddowiki.com/page/Dragonmark_of_Sentinel DragonMark of Sentinel]]
//    * The bloodline of House Deneith bestows on you a +2 bonus to your Intimidate skill.
//    * Activate this ability to cast Shield of Faith three times per rest.
//    * Shield of Faith gives you a deflection bonus to your AC.
//    * The amount increases with your character level.
//    */
//  case object LesserDragonmarkOfSentinel
//      extends DragonmarkFeat
//      with RaceRequisiteImpl
//      with RequiresAllOfRace
//      with RequiresAllOfFeat
//      with ActiveFeat
//      with OnSpellLikeAbilityEvent
//      with Passive {
//    override def allOfRace: Seq[(Race, Int)] = List((Human, 1))
//    override def allOfFeats: Seq[Feat] = Seq(LeastDragonmarkOfSentinel)
//    override def coolDown: Option[Duration] = Some(UnknownDuration)
//  }

//  /**
//    * [[https://ddowiki.com/page/Dragonmark_of_Sentinel DragonMark of Sentinel]]
//    * Activate this ability to harness the bloodline of House Deneith to cast Globe of Invulnerability once per rest.
//    * Also allows you to use your Least and Lesser Dragonmarks of Sentinel an extra time per rest.
//    * Globe of Invulnerability is an immobile sphere that can shield you and your allies,
//    * completely suppressing spell effects of level 4 and below.
//    */
//  case object GreaterDragonmarkOfSentinel
//      extends DragonmarkFeat
//      with RaceRequisiteImpl
//      with RequiresAllOfRace
//      with RequiresAllOfFeat
//      with ActiveFeat
//      with OnSpellLikeAbilityEvent {
//    override def allOfRace: Seq[(Race, Int)] = List((Human, 1))
//
//    override def allOfFeats: Seq[Feat] = Seq(LesserDragonmarkOfSentinel)
//    override def coolDown: Option[Duration] = Some(UnknownDuration)
//  }

  case object LeastDragonmarkOfPassage
    extends DragonmarkFeat with RaceRequisiteImpl with RequiresAllOfRace with Passive
    with ActiveFeat with OnSpellLikeAbilityEvent {
    override def allOfRace: Seq[(Race, Int)] = List((Human, 1))

    override def coolDown: Option[Duration] = Some(UnknownDuration)
  }

//  /**
//    * [[https://ddowiki.com/page/Least_Dragonmark_of_Warding Least Dragonmark of Warding]]
//    */
//  case object LesserDragonmarkOfWarding
//      extends DragonmarkFeat
//      with RaceRequisiteImpl
//      with RequiresAllOfRace
//      with RequiresAllOfFeat
//      with ActiveFeat
//      with OnSpellLikeAbilityEvent {
//    override def allOfRace: Seq[(Race, Int)] = List((Dwarf, 1))
//    override def coolDown: Option[Duration] = Some(UnknownDuration)
//    override def allOfFeats: Seq[Feat] = Seq(LeastDragonmarkOfWarding)
//  }

//  /**
//    * [[https://ddowiki.com/page/Least_Dragonmark_of_Warding Least Dragonmark of Warding]]
//    */
//  case object GreaterDragonmarkOfWarding
//      extends DragonmarkFeat
//      with RaceRequisiteImpl
//      with RequiresAllOfRace
//      with ActiveFeat
//      with OnSpellLikeAbilityEvent {
//    override def allOfRace: Seq[(Race, Int)] = List((Dwarf, 1))
//    override def coolDown: Option[Duration] = Some(UnknownDuration)
//  }

  /**
   * [[https://ddowiki.com/page/Dragonmark_of_Sentinel DragonMark of Sentinel]]
   */
  case object LeastDragonmarkOfSentinel
    extends DragonmarkFeat with RaceRequisiteImpl with RequiresAllOfRace with ActiveFeat
    with OnSpellLikeAbilityEvent with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((Human, 1))

    override def coolDown: Option[Duration] = Some(UnknownDuration)
  }

//  /**
//    * [[https://ddowiki.com/page/Least_Dragonmark_of_Scribing Least Dragonmark of Scribing]]
//    * Undocumented
//    */
//  case object LesserDragonmarkOfScribing
//      extends DragonmarkFeat
//      with RaceRequisiteImpl
//      with RequiresAllOfRace
//      with RequiresAllOfFeat
//      with ActiveFeat
//      with OnSpellLikeAbilityEvent {
//    override def allOfRace: Seq[(Race, Int)] = List((Gnome, 1))
//
//    override def allOfFeats: Seq[Feat] = Seq(LeastDragonmarkOfScribing)
//    override def coolDown: Option[Duration] = Some(UnknownDuration)
//  }

//  /**
//    * [[https://ddowiki.com/page/Least_Dragonmark_of_Scribing Least Dragonmark of Scribing]]
//    * Undocumented
//    */
//  case object GreaterDragonmarkOfScribing
//      extends DragonmarkFeat
//      with RaceRequisiteImpl
//      with RequiresAllOfRace
//      with RequiresAllOfFeat
//      with ActiveFeat
//      with OnSpellLikeAbilityEvent
//      with Passive {
//    override def allOfRace: Seq[(Race, Int)] = List((Gnome, 1))
//    override def allOfFeats: Seq[Feat] = Seq(LesserDragonmarkOfScribing)
//    override def coolDown: Option[Duration] = Some(UnknownDuration)
//  }

  /**
   * [[https://ddowiki.com/page/Least_Dragonmark_of_Warding Least Dragonmark of Warding]] The
   * bloodline of House Kundarak bestows on you a +2 bonus to your Search skill. Activate this
   * ability to cast Fire Trap three times per rest.
   */
  case object LeastDragonmarkOfWarding
    extends DragonmarkFeat with RaceRequisiteImpl with RequiresAllOfRace with ActiveFeat
    with OnSpellLikeAbilityEvent with Passive {
    override def coolDown: Option[Duration] = Some(UnknownDuration)

    override def allOfRace: Seq[(Race, Int)] = List((Dwarf, 1))
  }

  /**
   * [[https://ddowiki.com/page/Least_Dragonmark_of_Scribing Least Dragonmark of Scribing]]
   *
   * The bloodline of House Sivis bestows on you a +2 bonus to your Use Magic Device skill. Activate
   * this ability to scribe runes on your glove or gauntlet, casting the Shield spell.
   */
  case object LeastDragonmarkOfScribing
    extends DragonmarkFeat with RaceRequisiteImpl with RequiresAllOfRace with ActiveFeat
    with OnSpellLikeAbilityEvent with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((Gnome, 1))

    /**
     * @fixme
     *   Duration of 1 minute per character level
     * @return
     *   Duration
     */
    override def coolDown: Option[Duration] = Some(UnknownDuration)
  }
}
