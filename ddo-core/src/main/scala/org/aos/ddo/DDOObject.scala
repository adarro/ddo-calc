/**
  * Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *        http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package org.aos.ddo

import org.aos.ddo.model.attribute.{Attribute => Attrib}
import org.aos.ddo.model.item.weapon.{DeliveryType, Handedness, ProficiencyClass, WeaponCategory => wcat}
import org.aos.ddo.model.misc.Material

/**
  * Encapsulates any and all Items and objects within DDO
  */
object DDOObject {
  // Character Planning
  case class Character()
  case class Enhancement()
  case class Attribute()
  case class Skill()
  case class Feat()
  // Able to Equip
  case class Weapon(absoluteMinimumLevel: Option[Int],
                    baseValue: Option[MonetaryValue.Coins],
                    description: Option[String],
                    durability: Int,
                    hardness: Int,
                    material: Option[Material],
                    minimumLevel: Int,
                    umd: Int,
                    weight: Option[Int],
                    binding: Option[BindingFlags],
                    enchantments: Option[Seq[String]],
                    // Members declared in org.aos.ddo.Weapon
                    attackModifier: List[Attrib],
                    critical: Option[CriticalProfile],
                    damage: Option[DamageInfo],
                    damageModifier: List[Attrib],
                    handedness: List[Handedness],
                    proficiency: Option[ProficiencyClass],
                    upgradeable: UpgradeInfo,
                    weaponCategory: Option[wcat],
                    weaponType: Option[DeliveryType]) extends org.aos.ddo.model.item.weapon.Weapon
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
