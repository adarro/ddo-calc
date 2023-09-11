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
package io.truthencode.ddo.support.tree

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.support.SearchPrefix
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}

import scala.collection.immutable

sealed trait EpicDestiny
  extends EnumEntry with DestinySphere with DisplayName with FriendlyDisplay {

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified
   * "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "Destiny"

  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}
// Arcane
trait DraconicIncarnation extends EpicDestiny
trait Fatesinger extends EpicDestiny
trait Magister extends EpicDestiny
// Divine
trait Crusader extends EpicDestiny
trait ExaltedAngel extends EpicDestiny
trait UnyieldingSentinel extends EpicDestiny
// Martial
trait GrandmasterOfFlowers extends EpicDestiny
trait LegendaryDreadnought extends EpicDestiny
trait ShadowDancer extends EpicDestiny {
  override def displayText: String = "Shadow-dancer"
}
// Primal
trait FuryOfTheWild extends EpicDestiny
trait PrimalAvatar extends EpicDestiny
trait ShiradiChampion extends EpicDestiny

object EpicDestiny extends Enum[EpicDestiny] with SearchPrefix {
  override def values: immutable.IndexedSeq[EpicDestiny] = findValues

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified
   * "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "Destiny"

  case object DraconicIncarnation extends DraconicIncarnation

  case object Fatesinger extends Fatesinger

  case object Magister extends Magister

  // Divine
  case object Crusader extends Crusader

  case object ExaltedAngel extends ExaltedAngel

  case object UnyieldingSentinel extends UnyieldingSentinel

  // Martial
  case object GrandmasterOfFlowers extends GrandmasterOfFlowers

  case object LegendaryDreadnought extends LegendaryDreadnought

  case object ShadowDancer extends ShadowDancer

  // Primal
  case object FuryOfTheWild extends FuryOfTheWild

  case object PrimalAvatar extends PrimalAvatar

  case object ShiradiChampion extends ShiradiChampion
}
