/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2026
 *
 * Author: Andre White.
 * FILE: ItemClassification.scala
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
package io.truthencode.ddo.core.model.item

import enumeratum.{Enum, EnumEntry}

/**
 * Classifies an item by types
 *
 * Can be used to denote concepts such as wearable, craftable etc.
 */
sealed trait ItemClassification extends EnumEntry

object ItemClassification extends Enum[ItemClassification] {
  override def values: IndexedSeq[ItemClassification] = findValues

  /**
   * Includes potions, cakes, food, one-use items etc
   */
  case object Consumable extends ItemClassification

  /**
   * Item can be equipped / worn.
   */
  case object Equipment extends ItemClassification

  /**
   * Ingredients include many items that can be used in crafting
   */
  case object Ingredient extends ItemClassification

  /**
   * Physical part of a spell component
   */
  case object MaterialComponent extends ItemClassification

}
