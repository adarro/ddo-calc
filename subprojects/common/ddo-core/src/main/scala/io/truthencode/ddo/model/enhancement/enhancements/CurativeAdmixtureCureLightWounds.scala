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
import io.truthencode.ddo.model.enhancement.Tier1
import io.truthencode.ddo.model.enhancement.enhancements.classbased.ApothecaryTierOne
import io.truthencode.ddo.support.points.SpendablePoints
import io.truthencode.ddo.support.tree.TreeLike

trait CurativeAdmixtureCureLightWounds extends ApothecaryTierOne with ClassEnhancementImpl with CurativeAdmixtureBase {

  override lazy val description: Option[String] =
    Some(
      """Metamagic: Empower, Empower Heal, Maximize, Intensify Spell Point Cost: 4/3/2 Cooldown 12/8/4 seconds Primer Element: Gildleaf
        |
        |Infuses a Cure Light Wounds potion with magical energy, hurling it towards your target in an arc. The potion explodes in a small AOE that deals 1d6+2 plus 1 per caster level (maximum caster level 5) positive energy damage to nearby living allies and enemy undead. This infusion is affected by Artificer/Alchemist Knowledge: Potions.""".stripMargin
    )

  /**
   * Some enhancements can be taken multiple times (generally up to three)
   */
  override val ranks: Int = 3

  override protected def nameSource: String = "CureLightWounds"

  //  abstract override def displaySource: String = "Cure Serious Wounds"
  // override def displaySource: String = "Cure Serious Wounds"

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = Seq((Alchemist, 1))

  // override def progressionInTree: Seq[(TreeLike, SpendablePoints, Int)] = Seq((tree, 1))

  override def apCostPerRank: Int = 1

}
