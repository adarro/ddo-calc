/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: PermanentItem.scala
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
trait PermanentItem extends Item with CommonItemProperties {

  /**
   * Additional Effects such as enchantments on a Weapon
   */
  val effects: List[Effect] = Nil

  /**
   * //TODO: create set bonus case classes
   */
  val setBonus: List[SetItem] = Nil

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

  /**
   * List of possible enchantments that are either selected or randomly chosen. Int - amount from
   * paired list. I.e. 1 of or 2 of the paired enchantments.
   */
  val variableEnchantments: List[(Seq[String], Int)] = Nil

}
