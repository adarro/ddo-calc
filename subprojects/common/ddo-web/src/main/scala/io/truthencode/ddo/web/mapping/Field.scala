/**
 * Copyright (C) 2015 Andre White (adarro@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.truthencode.ddo.web.mapping

/**
 * Storage object used to contain text matches when scraping HTML text.
 */
object Field {

  /**
   * Extracts proficiency information, such as 'Simple Weapon Proficiency'
   */
  final val ProficiencyClass = "Proficiency Class"

  /**
   * Extracts Damage and Type information such as '1.50[1d4] + 3 Slash, Pierce, Magic'
   */
  final val DamageAndType = "Damage and Type"

  /**
   * Extracts Critical Threat Range, i.e. '19-20/x2'
   */
  final val CriticalThreatRange = "Critical threat range"

  /**
   * Extracts Weapon Type information, i.e. 'Dagger / Piercing weapons'
   */
  final val WeaponTypeAndDamageType = "Weapon Type"

  /**
   * Extracts Required Race information, i.e. 'Shadar-Kai'
   */
  final val RaceAbsRequired = "Race Absolutely Required"

  /**
   * Extracts Minimum Level, i.e. '15'
   */
  final val ML = "Minimum Level"

  /**
   * Extracts Required traits such as 'Good'
   */
  final val RequiredTrait = "Required Trait"

  /**
   * Extracts UMD check information
   *
   * i.e. Wiz(39) indicating a Wizard class can use or equip this item despite restrictions with a
   * successful UMD check of 39 or greater.
   */
  final val UMD = "Use Magical Device DC"

  /**
   * Extracts Handedness for weapons / shields
   *
   * i.e. main hand, off hand or two-handed
   */
  final val Handedness = "Handedness"

  /**
   * Extracts Attribute used to determine Attack modifier for Weapons
   *
   * Typically this will be STR (Strength) for Melee weapons and Dex for some Missile weapons.
   */
  final val AttackMod = "Attack Mod"

  /**
   * Extracts Attribute used to determine Damage Modifier for Weapons
   *
   * Typically this will be STR (Strength) for Melee weapons and Dex for some Missile weapons.
   */
  final val DamageMod = "Damage Mod"

  /**
   * Reads any binding enformation.
   *
   * i.e. Bound to Character / Account on Acquire or equipping
   */
  final val Binding = "Binding"

  /**
   * Reads the durability of the item
   *
   * Should be a number
   */
  final val Durability = "Durability"

  /**
   * Reads the material item is made from.
   *
   * i.e. Steel, Crystal etc.
   */
  final val Material = "Made from"

  /**
   * Reads the number representing the Hardness of an item.
   *
   * i.e. Steel will have a higher hardness than glass.
   */
  final val Hardness = "Hardness"

  /**
   * Reds the Base monetary value of the item.
   *
   * Generally expressed in platinum pieces. i.e. 6,000 pp 2 gp
   */
  final val BaseValue = "Base Value"

  /**
   * Reads the weight in pounds.
   *
   * i.e. 3 lbs
   */
  final val Weight = "Weight"

  /**
   * String description of where the item is located or how to obtain.
   *
   * i.e. Advance to level 15, End reward
   */
  final val Location = "Location"

  /**
   * Array of zero or more Enchantments
   *
   * Can be many different effects such a bonus to a stat, skill or ability etc.
   */
  final val Enchantments = "Enchantments"

  /**
   * Determines if an item can be upgraded to a better version.
   *
   * Generally, non-upgradeable items will be indicated by 'Not upgradeable' while upgradeable items
   * will have text descriptions and possibly a link on what / how to upgrade.
   */
  final val Upgradeable = "Upgradeable?"

  /**
   * Basic text with a description of the item.
   */
  final val Description = "Description" // likely multi-line text
}
