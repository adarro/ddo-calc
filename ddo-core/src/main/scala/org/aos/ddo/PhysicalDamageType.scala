/**
 * Copyright (C) 2015 Andre White (adarro@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aos.ddo

import enumeratum.{Enum,EnumEntry}

/** Basic damage for (generally) physical damage
  */
sealed trait PhysicalDamageType extends EnumEntry with NoDefault[PhysicalDamageType]

/** Basic damage enumeration for (generally) physical damage
  */
object PhysicalDamageType extends Enum[PhysicalDamageType] {
  /** Unique damage such as casts spell or other non-basic effect.
    */
  case object Special extends PhysicalDamageType
  /** Blunt force such as delivered by clubs maces and hammers
    */
  case object Bludgeon extends PhysicalDamageType
  /** Stabbing damage such as delivered by Rapiers, arrows, bolts
    */
  case object Pierce extends PhysicalDamageType
  /** Slicing damage such as delivered by Longswords, razors etc
    */
  case object Slash extends PhysicalDamageType
  /** Damage done by Magical means
    */
  case object Magic extends PhysicalDamageType

  val values: Seq[PhysicalDamageType] =  findValues
}
