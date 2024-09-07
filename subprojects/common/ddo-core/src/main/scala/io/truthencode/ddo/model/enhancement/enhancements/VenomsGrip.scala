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
import io.truthencode.ddo.model.enhancement.enhancements.classbased.{
  VileChemistTierFive,
  VileChemistTierFour
}
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.points.SpendablePoints
import io.truthencode.ddo.support.tree.TreeLike

trait VenomsGrip extends VileChemistTierFive with ClassEnhancementImpl {
  // Will Save +1
  // override val tree: ClassTrees = ClassTrees.Apothecary
  override lazy val description: Option[String] = Some(
    """+4 Intelligence, +10 Universal Spell Power, +3% Doublestrike and Doubleshot, +2 bonus Imbue Dice.
      |Passive: While your Reaction is Orchidium, you gain +5 Poison Spell Power, 2% Dodge, and 2% Dodge Cap""".stripMargin)

  /**
   * Some enhancements can be taken multiple times (generally up to three)
   */
  override val ranks: Int = 1

  override def apCostPerRank: Int = 1

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = Seq((Alchemist, 20))

  override def progressionInTree: Seq[(TreeLike, SpendablePoints, Int)] = Seq((tree, 40))

  override def displayText: String = "Venom's Grip"

}
