/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
import io.truthencode.ddo.model.race.Race
import io.truthencode.ddo.model.race.Race.{
  Dwarf,
  Elf,
  Gnome,
  HalfElf,
  HalfOrc,
  Halfling,
  Human
}
import io.truthencode.ddo.support.naming.FriendlyDisplay
import io.truthencode.ddo.support.requisite._

import scala.collection.immutable

/**
  * Created by adarr on 3/26/2017.
  */
sealed trait DragonmarkFeat
    extends Feat
    with FriendlyDisplay
    with SubFeatInformation
    with FeatMatcher
    with FeatRequisiteImpl {
  self: FeatType with Requisite with RequisiteType with Inclusion =>
  val matchFeat: PartialFunction[Feat, DragonmarkFeat] = {
    case x: DragonmarkFeat => x
  }
  val matchFeatById: PartialFunction[String, DragonmarkFeat] = {
    case x: String if DragonmarkFeat.namesToValuesMap.contains(x) =>
      DragonmarkFeat.withNameOption(x) match {
        case Some(y) => y
      }
  }
}

object DragonmarkFeat extends Enum[DragonmarkFeat] with FeatSearchPrefix {
  case object LeastDragonmarkOfStorm
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAllOfRace
      with Active
      with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((HalfElf, 1))
  }
  case object LeastDragonmarkOfShadow
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAllOfRace
      with Active
      with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((Elf, 1))
  }
  case object LeastDragonmarkOfFinding
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAllOfRace
      with Active
      with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((HalfOrc, 1))
  }
  case object LeastDragonmarkOfHealing
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAnyOfRace
      with Active
      with Passive {
    override def anyOfRace: Seq[(Race, Int)] = List((Halfling, 1), (Human, 1))
  }
  case object LeastDragonmarkOfMaking
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAnyOfRace
      with Active
      with Passive {
    override def anyOfRace: Seq[(Race, Int)] = List((Human, 1))
  }
  case object LeastDragonmarkOfPassage
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAllOfRace
      with Active
      with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((Human, 1))
  }
  case object LeastDragonmarkOfSentinel
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAllOfRace
      with Active
      with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((Human, 1))
  }
  case object LeastDragonmarkOfWarding
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAllOfRace
      with Active
      with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((Dwarf, 1))
  }
  case object LeastDragonmarkOfScribing
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAllOfRace
      with Active
      with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((Gnome, 1))
  }

  /**
    * Used when qualifying a search with a prefix.
    * Examples include finding "HalfElf" from qualified "Race:HalfElf"
    *
    * @return A default or applied prefix
    */
  override def searchPrefixSource: String = "Dragonmark: "

  override def values: immutable.IndexedSeq[DragonmarkFeat] = findValues
}
