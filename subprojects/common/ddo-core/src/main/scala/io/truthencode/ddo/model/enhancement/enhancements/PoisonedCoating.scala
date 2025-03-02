/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: PoisonedCoating.scala
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
import io.truthencode.ddo.model.enhancement.enhancements.classbased.{
  VileChemistCore,
  VileChemistTierOne
}
import io.truthencode.ddo.support.points.SpendablePoints
import io.truthencode.ddo.support.requisite.RequiresAllOfClass
import io.truthencode.ddo.support.tree.TreeLike

trait PoisonedCoating extends VileChemistTierOne with ClassEnhancementImpl {
  // Will Save +1
  // override val tree: ClassTrees = ClassTrees.Apothecary
  override lazy val description: Option[String] = Some(
    """Poisoned Coating: Imbue Toggle: While active, simple weapons deal an additional 1d6 Poison damage on hit. This damage scales with Spell Power.
      |You also gain +1 bonus Imbue Dice every 3 Alchemist levels (at 6, 9, 12, 15 and 18).
      |
      |Passive: While your Reaction is Orchidium, you gain +5 Poison Spell Power, 2% Dodge, and 2% Dodge Cap.
      |
      |Note: Mutually exclusive with all other Imbues.
      |Note: It doesn't state it clearly, but this does include an additional die at Lv. 3.""".stripMargin)

  /**
   * Some enhancements can be taken multiple times (generally up to three)
   */
  override val ranks: Int = 1

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = Seq((Alchemist, 3))

  override def apCostPerRank: Int = 1

  // override def allOfClass: Seq[(HeroicCharacterClass, Int)] = Seq((Alchemist, 1))

  override def progressionInTree: Seq[(TreeLike, SpendablePoints, Int)] = Seq((tree, 5))
}
