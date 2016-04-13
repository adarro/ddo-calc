/** Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package org.aos.ddo

import org.aos.ddo.weapon.{ Handedness, ProficiencyClass, WeaponCategory ⇒ wcat, WeaponType }

/** Encapsulates any and all Items and objects within DDO
  */
object DDOObject {
  // Character Planning
  case class Character()
  case class Enchancement()
  case class Attribute()
  case class Skill()
  case class Feat()
  // Able to Equip
  case class Weapon(val absoluteMinimumLevel: Option[Int],
    val baseValue: Option[MonetaryValue.Coins],
    val description: Option[String],
    val durability: Int,
    val hardness: Int,
    val material: Option[Material],
    val minimumLevel: Int,
    val umd: Int,
    val weight: Option[Int],
    val binding: Option[BindingFlags],
    // Members declared in org.aos.ddo.Weapon
    val attackModifier: List[Attributes],
    val critical: Option[CriticalProfile],
    val damage: Option[DamageInfo],
    val damageModifier: List[Attributes],
    val handedness: List[Handedness],
    val proficiency: Option[ProficiencyClass],
    val upgradeable: UpgradeInfo,
    val weaponCategory: Option[wcat],
    val weaponType: Option[WeaponType]) extends org.aos.ddo.weapon.Weapon
  case class Clothing()
  case class Jewelery()
  case class Armour()
  case class Quiver()
  // Boosters
  case class Spell()
  case class Potion()
  // Crafting
  case class Ingredient()
  case class Collectable()
  case class Misc()

}
