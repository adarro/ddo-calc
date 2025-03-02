/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Weapon.scala
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
package io.truthencode.ddo.model.item.weapon

import enumeratum.EnumEntry
import io.truthencode.ddo._
import io.truthencode.ddo.enumeration.EnumExtensions.EnumCompanionOps
import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.item.{PermanentItem, WearableItem}
import io.truthencode.ddo.support.dice.DamageDice
import io.truthencode.ddo.support.slots.WearLocation

import scala.language.implicitConversions

/**
 * Weapon Used only for protection... and kobolds.
 *
 * @note
 *   Template: Base Item Object Proficiency Class Simple Weapon Proficiency Icerazor shown.jpg
 *   Damage and Type 2 [1d6] + 6 Slash, Magic Critical threat range 20 / x2 Weapon Type Sickle /
 *   Slashing weapons Race Absolutely Required None Minimum Level 21 Required Trait None Use Magical
 *   Device DC No UMD needed Handedness Attack Mod STR Damage Mod STR Binding Bound to Character on
 *   Equip or Bound to Character on Acquire Durability 170 Made from Ice Hardness 22 Base Value
 *   8,400pp Weight 2 lbs Location The House of Broken Chains, End Chest ; Slavemaster Banister of
 *   House Avithoul, The Underdark ; Menace of the Underdark: City of Portals, Quest chain end
 *   reward Enchantments Spellcasting Implement +18 +6 Enhancement Bonus Glaciation +102 Ice Lore V
 *   Slicing Icy Burst Freezing Ice Upgradeable? Not upgradeable Composite Shortbow Description This
 *   wicked sickle is crafted from magical ice that will never melt. Any flesh it touches will
 *   instantly freeze to the blade, to be painfully ripped away when the weapon is withdrawn.
 */
trait Weapon extends PermanentItem with WearableItem with HandedWeapon {

  /**
   * Proficiency Class Equipping a item without having the corresponding proficiency may incur
   * penalties
   *
   * i.e. Repeating Heavy Crossbows require Martial Weapon Proficiency: Repeating Heavy Crossbows
   * (certain requirements may be bypassed or temporarily granted via race / class enhancements or
   * spells such as Tensor's Transformation
   */
  val proficiency: Option[ProficiencyClass]

  // Damage and Type: 1d6 Pierce
  val damage: Option[DamageDice]
  // Critical threat range 20/x3
  val critical: Option[CriticalProfile]

  /**
   * Weapon Type
   *
   * Weapon Type represents a general class of weapons, such as Ranged Weapons for bows, thrown for
   * small projectiles, etc.
   *
   * @see
   *   [[io.truthencode.ddo.model.item.weapon.DeliveryType]]
   */
  val weaponType: Option[DeliveryType]

  val weaponCategory: Option[WeaponCategory]

  /**
   * Handedness Represents how a weapon can be used / equipped. i.e. Daggers (MustContainAtLeastOne
   * Handed, Longbow two-handed)
   *
   * @note
   *   DDO Wiki seems a bit inconsistent.
   *   [[http://ddowiki.com/page/Composite_longbow Composite Longbow]] lists handedness as 'ranged'
   *   while [[http://ddowiki.com/page/Item:Bow_of_the_Silver_Flame Bow of the Silver Flame]] leaves
   *   this blank. For our purposes, the handedness will likely be 'twohanded', and matching against
   *   the [[io.truthencode.ddo.model.item.weapon.DeliveryType]] for Ranged should be enough to
   *   allow the UI to display either or none and understand that using a bow prevents equipping
   *   something else in the offhand.
   */
  val handedness: List[Handedness]

  /**
   * Default Modifiers Determines the ability stat used for attack or damage bonuses. Typically,
   * this will be STR for melee / bows to Hit and / or damage. Weapon finesse and similar may change
   * this to another stat such as DEX or INT for Insightful Weapons.
   */
  val damageModifier: List[Attribute]

  /**
   * @see
   *   [[damageModifier]]
   */
  val attackModifier: List[Attribute]

  val upgradeable: UpgradeInfo

  //  Named items: Category:Composite Shortbows

  /**
   * handwrap example Base Value: None Weight: None Proficiency Class: Simple weapons Damage and
   * Type: 1d3 Bludgeon (Halflings: 1d2 Bludgeon) Critical threat range 20/x2 Weapon Type:
   * Bludgeoning weapons Handedness: MustContainAtLeastOne-handed weapon Default Modifiers Damage:
   * STR Attack: STR Durability: Not applicable Made from: Flesh Hardness: N/A Named items:
   * Category:Handwraps
   */

  /**
   * restricts the slot to main hand or offhand. Override this to restrict further for two-handed or
   * other special cases
   */
  def allowedWearLocationFlags: Int = WearLocation.MainHand.bitValue | WearLocation.OffHand.bitValue
}

/**
 * Basic trait for allowing an item to be wielded using one or more hand slots.
 */
trait HandedWeapon extends Wearable

trait OneHandedWeapon extends HandedWeapon {
  lazy val allowedWearLocations: Seq[EnumEntry] = {
    WearLocation.fromMask(WearLocation.MainHand.bitValue | WearLocation.OffHand.bitValue) match {
      case Some(x) => x
      case _ => Nil
    }
  }
}

trait TwoHandedWeapon extends HandedWeapon {
  lazy val allowedWearLocations: Seq[EnumEntry] = {
    WearLocation.fromMask(WearLocation.MainHand.bitValue) match {
      case Some(x) => x
      case _ => List[WearLocation & Product & Serializable]()
    }
  }
}

case class CriticalThreatRange(range: Range, multiplier: Int)
