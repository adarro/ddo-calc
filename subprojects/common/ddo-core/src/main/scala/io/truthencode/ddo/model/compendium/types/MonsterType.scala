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
package io.truthencode.ddo.model.compendium.types

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

/**
  * Encompasses the main type of creature.
  * These types are used for favored enemies, bane weapons etc.
  * @note At this point, the aim is to be complete enough to support favored enemy creation and calculations.
  *       At some point in the future, a full compendium may be considered.
  */
sealed trait MonsterType extends EnumEntry

object MonsterType extends Enum[MonsterType] {
  case object Aberration extends MonsterType
  case object AirOutsider extends MonsterType
  case object Animal extends MonsterType
  case object Construct extends MonsterType
  case object Dragon extends MonsterType
  case object Dwarf extends MonsterType
  case object EarthOutsider extends MonsterType
  case object Elemental extends MonsterType
  case object Elf extends MonsterType
  case object EvilOutsider extends MonsterType
  case object Fey extends MonsterType
  case object FireOutsider extends MonsterType
  case object Giant extends MonsterType
  case object Gnoll extends MonsterType
  case object Goblinoid extends MonsterType
  case object GoodOutsider extends MonsterType
  case object Halfling extends MonsterType
  case object Human extends MonsterType
  case object LivingConstruct extends MonsterType
  case object MagicalBeast extends MonsterType
  case object MonstrousHumanoid extends MonsterType
  case object NativeOutsider extends MonsterType
  case object Ooze extends MonsterType
  case object Orc extends MonsterType
  case object Plant extends MonsterType
  case object Reptilian extends MonsterType
  case object Undead extends MonsterType
  case object Vermin extends MonsterType
  override def values: immutable.IndexedSeq[MonsterType] = findValues
}
