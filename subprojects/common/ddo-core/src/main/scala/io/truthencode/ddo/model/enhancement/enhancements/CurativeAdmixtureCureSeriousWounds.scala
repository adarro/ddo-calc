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

trait CurativeAdmixtureCureSeriousWounds
    extends ApothecaryCore
    with ClassEnhancementImpl
    with CurativeAdmixtureBase {

  override lazy val description: Option[String] =
    Some(
      """Curative Admixture Cure Serious Wounds SLA and Curative Admixture Inflict Serious Wounds SLA that share a cooldown. (Gildleaf spells)
               |Passive: + 1 Spell Penetration. While your Reaction is Verdanite, +5 Positive & Negative Spell Power.""".stripMargin
    )

  /**
    * Some enhancements can be taken multiple times (generally up to three)
    */
  override val ranks: Int = 1

  override protected def nameSource: String = "Cure Serious Wounds"
  //  abstract override def displaySource: String = "Cure Serious Wounds"
  // override def displaySource: String = "Cure Serious Wounds"

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = Seq((Alchemist, 6))

  override def progressionInTree: Seq[(TreeLike, SpendablePoints, Int)] = Seq((tree, 10))

  override def apCostPerRank: Int = 1

}
