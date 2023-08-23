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
package io.truthencode.ddo.support.points

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.Abbreviation
import io.truthencode.ddo.support.SearchPrefix
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}

import scala.collection.immutable

/**
 * Points that can be spent or allocated for enhancements and epic destinies etc.
 * @note
 *   Do we include Karma here? Likely just add a footnote and allow user to add fate points
 *   arbitrarily
 */
sealed trait SpendablePoints extends EnumEntry with DisplayName with FriendlyDisplay {
  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}

/**
 * Action Points are used for Enhancements
 */
trait ActionPoints extends SpendablePoints

trait EpicDestinyPoints extends SpendablePoints with Abbreviation {

  /**
   * The short form of the word
   */
  override val abbr: String = "EDP"

  /**
   * Expands the abbr to its full value
   */
  override def toFullWord: String = entryName.splitByCase

  /**
   * @inheritdoc
   */
  override protected def nameSource: String = abbr.splitByCase.toPascalCase
}

/**
 * Accrued by completing quests on Reaper difficulty
 */
trait SurvivalPoints extends SpendablePoints

/**
 * Unlocked by achieving Karma
 */
trait FatePoints extends SpendablePoints

object SpendablePoints extends Enum[SpendablePoints] with SearchPrefix {

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified
   * "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "Points"

  override def values: immutable.IndexedSeq[SpendablePoints] = findValues
  case object ActionPoints extends ActionPoints
  case object SurvivalPoints extends SurvivalPoints
  case object EpicDestinyPoints extends EpicDestinyPoints
  case object FatePoints extends FatePoints
}
