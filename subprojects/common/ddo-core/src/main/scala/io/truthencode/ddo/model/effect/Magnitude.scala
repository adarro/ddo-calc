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
package io.truthencode.ddo.model.effect

import io.truthencode.ddo.support.dice.DamageDice

/**
 * Represents the chance and amount of damage
 *
 * @note
 *   Example below is for the Elemental Guards Minor = 50% chance of 1d4 damage Lesser = 1d4 damage,
 *   base price modifier: +1 Roman Numeral suffix = 1d4 damage per level (Guard V = 5d4 damage) No
 *   prefix or Roman Numerals (regular) = 1d8 damage, base price modifier: +2
 */
trait Magnitude {

  /**
   * Percent chance to occur
   */
  val chance: Int

  /**
   * Damage Dice
   */
  val damage: DamageDice

  /**
   * Base Price Modifier
   */
  val bpm: Int
}
