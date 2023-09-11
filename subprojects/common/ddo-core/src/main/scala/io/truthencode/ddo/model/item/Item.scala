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
package io.truthencode.ddo.model.item

import io.truthencode.ddo.MonetaryValue.Coins
import io.truthencode.ddo.model.effect.Effect
import io.truthencode.ddo.model.misc.Material
import io.truthencode.ddo.model.race.Race
import io.truthencode.ddo.{BindingFlags, MetaData, SetItem}

/**
 * A general Item, which can be a Weapon, Armor, potion, scroll etc.
 */
trait Item extends MetaData {

  /**
   * A Friendly, Kobold readable description or explanation of the item.
   */
  val description: Option[String]

  /**
   * requiredRaces - list of races the player or pet MUST be to naturally equip the item unless they
   * can bypass with a high enough UMD.
   */
  val requiredRaces: List[Race] = Nil

  /**
   * A list of races that a player MUST be to equip the item.
   */
  val absoluteRequiredRaces: List[Race] = Nil

  /**
   * A list of races the player MUST NOT be in order to wear.
   * @example
   *   Warforged can NOT wear plate armor.
   */
  val absoluteRestrictedRaces: List[Race] = Nil

  /**
   * Represents the Minimum level a player must be to equip this item.
   */
  val minimumLevel: Int

  /**
   * Represents the absolute minimum level of the item. This is usually due to augments or crafting
   * or other alterations to a base item.
   */
  val absoluteMinimumLevel: Option[Int]

  /**
   * Use Magical Device [[http://ddowiki.com/page/Use_Magic_Device UMD]] Skill level needed to
   * bypass certain restrictions such as using wands below level or out of class or bypassing race
   * restrictions.
   */
  val umd: Int

  /**
   * Additional Effects such as enchantments on a Weapon
   */
  val effects: List[Effect] = Nil

  /**
   * //TODO: create set bonus case classes
   */
  val setBonus: List[SetItem] = Nil

  /**
   * Represents monetary in game amount. plat / gold / copper. Low Priority i.e. Base Value: 7pp 5gp
   * TODO: Base Value may need to be a double or something to better represent
   */
  val baseValue: Option[Coins]

  /**
   * Weight of item, in pounds i.e. Weight: 2 lbs
   */
  val weight: Option[Int]

  /**
   * Durability
   *
   * The durability is the toughness of the item. The higher the durability, the more damage it can
   * withstand before breaking. The material also contributes to this value.
   *
   * @note
   *   For purposes of this API, only maximum durability is considered. Not instance. i.e.
   *   Durability 120 means 120 / 120, your actual item may have wear.
   */
  val durability: Int

  /**
   * Material what the item is "made from" I.e. bows - typically wood, plate mail may be steel or
   * dwarven iron.
   *
   * @example
   *   Druids (and their pets) can wear leather armour. Generally they wear light armour or cloth,
   *   however, there are a few medium armours made from chiten or carapace that would not violate
   *   the druidic oath.
   */
  val material: Option[Material]

  /**
   * Hardness Generally based on the material and influences how quickly durability is decreased.
   * Adamantum and Diamond would have a higher rating that wood.
   */
  val hardness: Int

  /**
   * Indicates the binding status, if any for the item.
   */
  val binding: Option[BindingFlags]

  /**
   * List of enchantments
   */
  val enchantments: Option[Seq[String]]

}
