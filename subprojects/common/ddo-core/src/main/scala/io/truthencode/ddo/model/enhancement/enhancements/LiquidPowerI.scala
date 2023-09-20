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
import io.truthencode.ddo.model.enhancement.enhancements.classbased.BombardierCore
import io.truthencode.ddo.support.StringUtils._
import io.truthencode.ddo.support.points.SpendablePoints
import io.truthencode.ddo.support.tree.TreeLike

trait LiquidPowerI extends BombardierCore with ClassEnhancementImpl {

  override lazy val description: Option[String] = Some(
    """Select an Element to specialize in.
      |BombadierLiquidPowerAcidI.png Liquid Power Acid: You gain +1 Caster Level with Acid Spells.
      |BombadierLiquidPowerColdI.png Liquid Power Cold: You gain +1 Caster Level with Cold Spells.
      |BombadierLiquidPowerElectricI.png Liquid Power Electric: You gain +1 Caster Level with Electric Spells.
      |BombadierLiquidPowerFireI.png Liquid Power Fire: You gain +1 Caster Level with Fire Spells.
      |BombadierLiquidPowerPoisonI.png Liquid Power Poison: You gain +1 Caster Level with Poison Spells.
      |While in Pyrite Reaction, you gain +5 Fire, Cold, Electric, Acid, and Poison Spell Power
      |""".stripMargin
  )

  /**
   * Some enhancements can be taken multiple times (generally up to three)
   */
  override val ranks: Int = 1

  override def displayText: String = displaySource.replaceNumbersWithRomanNumerals

  override def apCostPerRank: Int = 1

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = Seq((Alchemist, 6))

  override def progressionInTree: Seq[(TreeLike, SpendablePoints, Int)] = Seq((tree, 10))

  override protected def nameSource: String = "Liquid Power I".replaceRomanNumerals
}
