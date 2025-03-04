/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: DeityFeat.scala
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
import io.truthencode.ddo.support.naming.FriendlyDisplay
import io.truthencode.ddo.support.requisite._

import scala.collection.immutable

/**
 * Created by adarr on 2/14/2017.
 */
sealed trait DeityFeat
  extends Feat with FriendlyDisplay with SubFeatInformation with ClassRequisiteImpl with FeatMatcher
  with ReligionFeatBaseImpl with FeaturesImpl {
  self: FeatType & Requisite & Inclusion & RequisiteType & Features =>

  val matchFeat: PartialFunction[Feat, DeityFeat] = { case x: DeityFeat =>
    x
  }

  val matchFeatById: PartialFunction[String, DeityFeat] = {

    case x: String if DeityFeat.namesToValuesMap.contains(x) =>
      DeityFeat.withNameOption(x) match {
        case Some(y) => y
      }
  }
}

// scalastyle:off number.of.methods
object DeityFeat extends Enum[DeityFeat] with FeatSearchPrefix with FeatMatcher {

  override lazy val values: immutable.IndexedSeq[DeityFeat] = findValues
  override val matchFeat: PartialFunction[Feat, ? <: Feat] = { case x: DeityFeat =>
    x
  }
  override val matchFeatById: PartialFunction[String, ? <: Feat] = {
    case x: String if EpicFeat.namesToValuesMap.contains(x) =>
      DeityFeat.withNameOption(x) match {
        case Some(y) => y
      }
  }

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified
   * "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "Deity Feat"

  case object FollowerOfAureon extends DeityFeat with FollowerOfAureon

  case object FollowerOfTheBloodOfVol extends DeityFeat with FollowerOfTheBloodOfVol

  case object FollowerOfTheLordOfBlades extends DeityFeat with FollowerOfTheLordOfBlades

  case object FollowerOfOlladra extends DeityFeat with FollowerOfOlladra

  case object FollowerOfOnatar extends DeityFeat with FollowerOfOnatar

  case object FollowerOfTheSilverFlame extends DeityFeat with FollowerOfTheSilverFlame

  case object FollowerOfTheSovereignHost extends DeityFeat with FollowerOfTheSovereignHost

  case object FollowerOfTheUndyingCourt extends DeityFeat with FollowerOfTheUndyingCourt

  case object FollowerOfVulkoor extends DeityFeat with FollowerOfVulkoor

  case object FavoredByAmaunator extends DeityFeat with FavoredByAmaunator

  case object FavoredByHelm extends DeityFeat with FavoredByHelm

  case object FavoredBySilvanus extends DeityFeat with FavoredBySilvanus

  case object ChildOfAureon extends DeityFeat with ChildOfAureon

  case object ChildOfTheBloodOfVol extends DeityFeat with ChildOfTheBloodOfVol

  case object ChildOfTheLordOfBlades extends DeityFeat with ChildOfTheLordOfBlades

  case object ChildOfOlladra extends DeityFeat with ChildOfOlladra

  case object ChildOfOnatar extends DeityFeat with ChildOfOnatar

  case object ChildOfTheSilverFlame extends DeityFeat with ChildOfTheSilverFlame

  case object ChildOfTheSovereignHost extends DeityFeat with ChildOfTheSovereignHost

  case object ChildOfTheUndyingCourt extends DeityFeat with ChildOfTheUndyingCourt

  case object ChildOfVulkoor extends DeityFeat with ChildOfVulkoor

  case object ChildOfAmaunator extends DeityFeat with ChildOfAmaunator

  case object ChildOfHelm extends DeityFeat with ChildOfHelm

  case object ChildOfSilvanus extends DeityFeat with ChildOfSilvanus

  case object AmaunatorsFlames extends DeityFeat with AmaunatorsFlames

  case object AureonsInstruction extends DeityFeat with AureonsInstruction

  case object TheBloodIsTheLife extends DeityFeat with TheBloodIsTheLife

  case object BladeswornTransformation extends DeityFeat with BladeswornTransformation

  case object LuckOfOlladra extends DeityFeat with LuckOfOlladra

  case object OnatarsForge extends DeityFeat with OnatarsForge

  case object SilverFlameExorcism extends DeityFeat with SilverFlameExorcism

  case object UnyieldingSovereignty extends DeityFeat with UnyieldingSovereignty

  case object UndyingCall extends DeityFeat with UndyingCall

  case object VulkoorsAvatar extends DeityFeat with VulkoorsAvatar

  case object EverWatchful extends DeityFeat with EverWatchful

  case object BlessingOfSilvanus extends DeityFeat with BlessingOfSilvanus

  // Level 12 Deity Feats
  case object BelovedOfAureon extends DeityFeat with BelovedOfAureon

  case object BelovedOfTheBloodOfVol extends DeityFeat with BelovedOfTheBloodOfVol

  case object BelovedOfTheLordOfBlades extends DeityFeat with BelovedOfTheLordOfBlades

  case object BelovedOfOlladra extends DeityFeat with BelovedOfOlladra

  case object BelovedOfOnatar extends DeityFeat with BelovedOfOnatar

  case object BelovedOfTheSilverFlame extends DeityFeat with BelovedOfTheSilverFlame

  case object BelovedOfTheSovereignHost extends DeityFeat with BelovedOfTheSovereignHost

  case object BelovedOfTheUndyingCourt extends DeityFeat with BelovedOfTheUndyingCourt

  case object BelovedOfVulkoor extends DeityFeat with BelovedOfVulkoor

  case object BelovedOfAmaunator extends DeityFeat with BelovedOfAmaunator

  case object BelovedOfHelm extends DeityFeat with BelovedOfHelm

  case object BelovedOfSilvanus extends DeityFeat with BelovedOfSilvanus

  case object DamageReductionSilver extends DeityFeat with DamageReductionSilver

  case object DamageReductionAdamantine extends DeityFeat with DamageReductionAdamantine

  case object DamageReductionColdIron extends DeityFeat with DamageReductionColdIron

  case object DamageReductionGood extends DeityFeat with DamageReductionGood
}
