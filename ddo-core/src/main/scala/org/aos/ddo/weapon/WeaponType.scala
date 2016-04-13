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
package org.aos.ddo.weapon

import org.aos.ddo.{ Enum, NoDefault }

/** used to denote the basic use of the weapon.
  *
  *
  */
sealed trait WeaponType extends WeaponType.Value with NoDefault[WeaponType.Value]
/** Enum to constrain allowed Weapon Types
  */
object WeaponType extends Enum[WeaponType] {
  /** Launches projectiles
    *
    * This includes most bows, crossbows etc.
    */
  case object Ranged extends WeaponType
  /** Close combat
    *
    * Includes swords, daggers, clubs etc
    */
  case object Melee extends WeaponType
  /** Weapons that can be thrown
    *
    * Ninja stars, throwing knives and hammers etc
    */
  case object Thrown extends WeaponType
  /** Weapons that deal damage in multiple or otherwise unique ways.
    */
  case object Special extends WeaponType
  override val values: List[org.aos.ddo.weapon.WeaponType with Product with Serializable] = List(Melee, Ranged, Thrown, Special)
}
