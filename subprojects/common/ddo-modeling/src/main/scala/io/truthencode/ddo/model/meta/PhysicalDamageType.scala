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
package io.truthencode.ddo.model.meta

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.NoDefault
import io.truthencode.ddo.model.effect._

/**
 * Basic damage for (generally) physical damage such as melee weapons or projectiles (arrows /
 * bolts) in addition to physical components to spells such as bludgeon damage from 'Ice Storm'.
 */
sealed trait PhysicalDamageType extends EnumEntry with Damage with NoDefault[PhysicalDamageType] {
  self: DamageType =>
}

/**
 * Basic damage enumeration for (generally) physical damage
 */
object PhysicalDamageType extends Enum[PhysicalDamageType] {

  val values = findValues

  /**
   * Unique damage such as casts spell or other non-basic effect.
   */
  case object Special extends PhysicalDamageType with UntypedPhysicalDamage

  /**
   * Blunt force such as delivered by clubs maces and hammers and the crushing boulders of an ice
   * storm
   */
  case object Bludgeon extends PhysicalDamageType with TypedPhysicalDamage with Bludgeoning

  /**
   * Stabbing damage such as delivered by Rapiers, arrows, bolts
   */
  case object Pierce extends PhysicalDamageType with TypedPhysicalDamage with Piercing

  /**
   * Slicing damage such as delivered by Longswords, razors etc
   */
  case object Slash extends PhysicalDamageType with TypedPhysicalDamage with Slashing

  /**
   * Damage done by Magical means such as a spell or a weapon with an enhancement of +1 or better
   */
  case object Magic extends PhysicalDamageType with MagicalDamage
}
