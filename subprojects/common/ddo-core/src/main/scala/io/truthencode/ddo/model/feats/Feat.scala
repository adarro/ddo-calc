/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Feat.scala
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
import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.model.effect.SourceInfo
import io.truthencode.ddo.model.effect.features.Features
import io.truthencode.ddo.model.race.Race
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}
import io.truthencode.ddo.support.requisite._

import scala.collection.immutable.IndexedSeq

/**
 * Created by adarr on 2/14/2017.
 */
trait Feat
  extends EnumEntry with DisplayName with FriendlyDisplay with SubFeatInformation with SourceInfo
  with Features {
  self: FeatType & Requisite & Inclusion =>

  override val sourceId: String = s"Feat:$entryName"
  override val sourceRef: AnyRef = this

  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}

object Feat extends Enum[Feat] with FeatSearchPrefix with LazyLogging {
  lazy val racialFeats: IndexedSeq[Feat & RaceRequisite] = {
    Feat.values.collect(fnRacialFeats)
  }

  /**
   * Filters feats which belong to racial / general feats only.
   *
   * This removes feats that happen to have some racial aspect but are considered under another
   * category, such as a Deity Based feat that requires classes as well as a race.
   *
   * @note
   *   Not sure if we really need this method in production. May only be useful for detecting /
   *   Acceptance testing subsets
   */
  val fnPureRacialFeat: PartialFunction[RaceRequisite, Feat & RaceRequisite] = {
    case x: RacialFeat => x
    case x: GeneralFeat => x
  }

  val fnRacialFeats: PartialFunction[Feat, Feat & RaceRequisite] = { case x: RaceRequisite =>
    x
  }

  val fnTacticalFeats: PartialFunction[Feat, Feat & Tactical] = { case x: Tactical =>
    x
  }

  def featsFromRace(race: Race, opt: RequirementOption*): Seq[Feat] = {

    racialFeats.filter { f =>
      containsInTuple(race, fnOptionsToRaceSequence(f, opt*))
    }.filterNot {
      case _: DeityFeat => true
      case _ => false
    }
  }

  def containsInTuple[T](target: T, search: Seq[(T, ?)]*): Boolean = {
    val s: Seq[(T, ?)] = search.flatMap(_.zipWithIndex).map(_._1)
    s.exists(_._1 == target)
  }

  def fnOptionsToRaceSequence(req: RaceRequisite, options: RequirementOption*): Seq[(Race, Int)] = {
    val seqOfSeq =
      for opt <- options
      yield fnOptionToRequisite(req = req, opt = opt)
    seqOfSeq.flatten
  }

  def fnOptionToRequisite(req: RaceRequisite, opt: RequirementOption): Seq[(Race, Int)] = {
    opt match {
      case RequirementOption.AutoGrant => req.grantsToRace
      case RequirementOption.Available => req.anyOfRace ++ req.allOfRace

      //      case _ => logger.warn(s"Feature not yet supported: Can not determine values for option $opt")
      //        throw NotImplementedException
      //      case RequirementOption.SelectableAsBonus => ???
      //      case RequirementOption.SelectableWithRestriction => ???
    }
  }

  def featsFromRace(race: Race): Seq[Feat] = {
    racialFeats.filter { f =>
      containsInTuple(race, fnOptionsToRaceSequence(f, RequirementOption.AutoGrant))
    }
  }

  override def values: IndexedSeq[Feat] =
    GeneralFeat.values ++ ClassFeat.values ++ RacialFeat.values ++ MetaMagicFeat.values ++ DeityFeat.values ++ EpicFeat.values ++ SpecialFeat.values
}
