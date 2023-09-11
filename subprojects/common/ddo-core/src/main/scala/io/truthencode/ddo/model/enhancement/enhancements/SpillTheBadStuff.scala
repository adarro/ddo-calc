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

import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Alchemist
import io.truthencode.ddo.model.enhancement.enhancements.classbased.ApothecaryCore
import io.truthencode.ddo.support.points.SpendablePoints
import io.truthencode.ddo.support.tree.TreeLike

trait SpillTheBadStuff extends ApothecaryCore with ClassEnhancementImpl {

  override lazy val description: Option[String] = Some(
    "Gildleaf Offensive SLA: Deals 1d6+4 per Caster Level of a random type of damage to enemies in a short cone. The damage all scales with Positive Spell Power. Affected enemies have a chance to be Blinded, Dazed, Silenced, Tripped, Stunned, or Paralyzed. (Fortitude save negates).\nPassive: While your Reaction is Verdanite, +2% Positive & Negative Spell Critical Damage & 5% Magical Efficiency (reduced spell point cost)"
  )

  /**
   * Some enhancements can be taken multiple times (generally up to three)
   */
  override val ranks: Int = 1

  override def apCostPerRank: Int = 1

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = Seq((Alchemist, 12))

  override def progressionInTree: Seq[(TreeLike, SpendablePoints, Int)] = Seq((tree, 20))
}
