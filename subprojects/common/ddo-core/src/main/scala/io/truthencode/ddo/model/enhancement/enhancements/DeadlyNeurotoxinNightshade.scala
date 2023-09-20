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
package io.truthencode.ddo.model.enhancement.enhancements

import io.truthencode.ddo.model.enhancement.enhancements.classbased.ApothecaryTierFive

trait DeadlyNeurotoxinNightshade extends ApothecaryTierFive with ClassEnhancementImpl {

  override lazy val description: Option[String] = Some(
    """Choose between Deadly Neurotoxin and Nightshade.
      |Deadly Neurotoxin: Offensive Gildleaf or Ceruleite Spells will reduce your targets Armor Class and Magical Resistance rating by -1 per stack, max 5.
      |
      |Nightshade: Spell point Cost: 10, Cooldown: 30 seconds, Metamagics: Quicken, Accelerate Primer: Gildleaf
      |
      |Target enemy falls asleep for 6 seconds with no save.""".stripMargin
  )

  /**
   * Some enhancements can be taken multiple times (generally up to three)
   */
  override val ranks: Int = 1

  override def displayText: String = "Deadly Neurotoxin/Nightshade"

  override def apCostPerRank: Int = 1

}
