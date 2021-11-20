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
package io.truthencode.ddo.model.favor

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.support.SearchPrefix

import scala.collection.immutable.IndexedSeq

/**
  * [[http://ddowiki.com/page/FavorPatron FavorPatron]] FavorPatron can be gained for a faction which can provide various benefits in-game.
  */
sealed trait FavorPatron extends EnumEntry {
  val maxFavor: Int
  val availableFreeFavor: Int = 0
}

object FavorPatron extends Enum[FavorPatron] with SearchPrefix {

  /**
    * Used when qualifying a search with a prefix.
    * Examples include finding "HalfElf" from qualified "Race:HalfElf"
    *
    * @return A default or applied prefix
    */
  override def searchPrefixSource: String = "FavorPatron"

  case object AgentsOfArgonnessen extends FavorPatron {
    override val maxFavor: Int = 312
  }

  case object HouseCannith extends FavorPatron {
    override val maxFavor: Int = 225
  }


  case object TheCoinLords extends FavorPatron {
    override val maxFavor: Int = 608
    override val availableFreeFavor: Int = 261
  }

  case object HouseDeneith extends FavorPatron {
    override val maxFavor: Int = 318
    override val availableFreeFavor: Int = 135
  }

  case object TheFreeAgents extends FavorPatron {
    override val maxFavor: Int = 624
    override val availableFreeFavor: Int = 147
  }

  case object TheGatekeepers extends FavorPatron {
    override val maxFavor: Int = 219
  }

  case object TheHarpers extends FavorPatron {
    override val maxFavor: Int = 186
  }

  case object HouseJorasco extends FavorPatron {
    override val maxFavor: Int = 282
    override val availableFreeFavor: Int = 141
  }

  case object HouseKundarak extends FavorPatron {
    override val maxFavor: Int = 342
    override val availableFreeFavor: Int = 162
  }

  case object HousePhiarlan extends FavorPatron {
    override val maxFavor: Int = 372
    override val availableFreeFavor: Int = 93
  }

  case object PurpleDragonKnights extends FavorPatron {
    override val maxFavor: Int = 417
    override val availableFreeFavor: Int = 33
  }

  case object TheSilverFlame extends FavorPatron {
    override val maxFavor: Int = 540
    override val availableFreeFavor: Int = 27

  }

  case object TheTwelve extends FavorPatron {
    override val maxFavor: Int = 339
    override val availableFreeFavor: Int = 18
  }

  case object TheYugoloth extends FavorPatron {
    override val maxFavor: Int = 111
  }

  override def values: IndexedSeq[FavorPatron] = findValues
}