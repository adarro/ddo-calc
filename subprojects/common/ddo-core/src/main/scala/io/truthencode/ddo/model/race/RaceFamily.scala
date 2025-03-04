/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: RaceFamily.scala
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
package io.truthencode.ddo.model.race

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable.{HashSet, IndexedSeq}

/**
 * Groups Individual race types into main families. Used primarily for Race restricted equipment
 * i.e.
 */
sealed trait RaceFamily extends EnumEntry {
  def includedRaces: Set[Race]
}

sealed trait RaceFamilyImpl extends RaceFamily {
  override def includedRaces: Set[Race] = new HashSet[Race]()
}

protected trait Elvish extends RaceFamily {
  abstract override def includedRaces: Set[Race] =
    super.includedRaces + Race.Morninglord + Race.Elf + Race.HalfElf + Race.DrowElf
}

protected trait HumanKind extends RaceFamily {
  abstract override def includedRaces: Set[Race] =
    super.includedRaces + Race.Human + Race.HalfElf + Race.PurpleDragonKnight
}

protected trait DragonKind extends RaceFamily {
  abstract override def includedRaces: Set[Race] =
    super.includedRaces + Race.DragonBorn
}

protected trait Dwarven extends RaceFamily {
  abstract override def includedRaces: Set[Race] =
    super.includedRaces + Race.Dwarf
}

protected trait Gnomish extends RaceFamily {
  abstract override def includedRaces: Set[Race] =
    super.includedRaces + Race.Gnome + Race.DeepGnome
}

protected trait Construct extends RaceFamily {
  abstract override def includedRaces: Set[Race] =
    super.includedRaces + Race.Warforged + Race.Bladeforged
}

protected trait HalflingKind extends RaceFamily {
  abstract override def includedRaces: Set[Race] =
    super.includedRaces + Race.Halfling
}

protected trait ShadarkaiKind extends RaceFamily {
  abstract override def includedRaces: Set[Race] =
    super.includedRaces + Race.Shadarkai
}

protected trait Orcish extends RaceFamily {
  abstract override def includedRaces: Set[Race] =
    super.includedRaces + Race.HalfOrc
}

object RaceFamily extends Enum[RaceFamily] {

  override def values: IndexedSeq[RaceFamily] = findValues

  /**
   * Includes Standard Elves along with Drow, Sun and Half-elves
   */
  case object Elven extends RaceFamilyImpl with Elvish

  /**
   * Includes Warforged, Bladeforged TODO: needs to include any race with Construct Essence Feat
   */
  case object Construct extends RaceFamilyImpl with Construct

  /**
   * Includes Humans, Purple Dragon Knights and Half-elves
   */
  case object Human extends RaceFamilyImpl with HumanKind

  /**
   * Dwarven Lineage
   */
  case object Dwarven extends RaceFamilyImpl with Dwarven

  /**
   * Includes Gnomes and their Underdark bretheren, Deep Gnomes
   */
  case object Gnomish extends RaceFamilyImpl with Gnomish

  /**
   * The Halfling Race
   */
  case object Halfing extends RaceFamilyImpl with HalflingKind

  /**
   * Includes Half-Orcs
   */
  case object Orcish extends RaceFamilyImpl with Orcish

  /**
   * Includes DragonBorn
   */
  case object DragonBorn extends RaceFamilyImpl with DragonKind

  /**
   * Includes the Iconic Shadarkai
   */
  case object Shadarkai extends RaceFamilyImpl with ShadarkaiKind
}
