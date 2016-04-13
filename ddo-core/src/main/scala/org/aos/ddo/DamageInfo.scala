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
/** Contains Damage information based on Dice
  *
  * @example
  * Given 3[1d4] + 2
  * weaponModifier : 3
  * dice : 1d4
  * extra : 2
  */
trait DamageInfo {
  /** Multiplies the dice by the specified number
    *
    * @example weaponModifier 3[1d4] = 1[3d4]
    */
  val weaponModifier: Int
  /** Number of sides of the dice
    *
    * 6 represents a 6 sided die
    */
  val dice: String
  /** Adds or subtracts to the value of the dice
    *
    * 1d8 + 3, 3 is the extra
    */
  val extra: Int
  /** List of damage types such as Slash, Pierce, Magic, Good etc
    */
  val damageType: List[String]
}
