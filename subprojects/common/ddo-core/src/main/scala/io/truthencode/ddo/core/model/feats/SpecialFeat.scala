/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: SpecialFeat.scala
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
import io.truthencode.ddo.model.effect.features.{Features, FeaturesImpl}
import io.truthencode.ddo.support.naming.FriendlyDisplay
import io.truthencode.ddo.support.requisite.{Inclusion, Requisite}

/**
 * Meta / special feats such as Feat Respec Tokens which generally have meta-mechanic functions.
 */
sealed trait SpecialFeat
  extends Feat with FriendlyDisplay with SubFeatInformation with FeaturesImpl {
  self: FeatType & Requisite & Inclusion & Features =>

}

object SpecialFeat
  extends Enum[SpecialFeat] with FeatSearchPrefix with FeatMatcher with LazyLogging {

  val matchFeat: PartialFunction[Feat, SpecialFeat] = { case x: SpecialFeat =>
    x
  }

  val matchFeatById: PartialFunction[String, SpecialFeat] = {
    case x: String if SpecialFeat.namesToValuesMap.contains(x) =>
      SpecialFeat.withNameOption(x) match {
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
  override def searchPrefixSource: String = "Special"

  override def values: IndexedSeq[SpecialFeat] = findValues

  //   Exchange feats
  case object FeatRespecToken extends SpecialFeat with FeatRespecToken
}
