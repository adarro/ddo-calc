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
package org.aos.ddo.model.feats

import org.aos.ddo.model.item.weapon.ExoticWeapon
import org.aos.ddo.support.naming.Prefix
import org.aos.ddo.support.requisite._

/** Icon Feat Exotic Weapon Proficiency.png
  * Exotic Weapon Proficiency - Passive
  * This feat negates the -4 penalty from using any of the exotic weapons while untrained.
  * Bastard Sword and Dwarven Waraxe deal grazing hits as if they were a two handed weapon if they are the only weapon wielded by a proficient user.
  * This feat must be taken for separate exotic weapons.
  * *
  * Strength 13 for Bastard Sword and Dwarven Waraxe
  * Base Attack Bonus +1,
  */
protected[feats] trait ExoticWeaponProficiencyBase
  extends FeatRequisiteImpl
    with RaceRequisite
    with ClassRequisiteImpl
    with Prefix
    with Passive
    with RequiresBaB
    with WeaponProficiencyBase
    with ExoticWeapon
    with FighterBonusFeat {
  self: GeneralFeat =>
  /**
    * Delimits the prefix and text.
    */
  override protected val prefixSeparator: String = ": "

  override def requiresBaB: Int = 1

  override def prefix: Option[String] = Some("Exotic Weapon Proficiency")

}
