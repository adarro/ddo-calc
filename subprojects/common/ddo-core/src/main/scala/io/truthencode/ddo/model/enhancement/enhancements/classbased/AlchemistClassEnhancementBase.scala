/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: AlchemistClassEnhancementBase.scala
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
package io.truthencode.ddo.model.enhancement.enhancements.classbased

import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Alchemist
import io.truthencode.ddo.model.enhancement.enhancements.ClassEnhancement
import io.truthencode.ddo.model.enhancement.{
  AlchemistEnhancement,
  Apothecary,
  Bombardier,
  VileChemist
}
import io.truthencode.ddo.support.requisite.RequiresPointsAvailable
import io.truthencode.ddo.support.tree.ClassTrees

trait AlchemistClassEnhancementBase {
  self: ClassEnhancementBase =>
  override def validClasses: Seq[HeroicCharacterClass] = Seq(HeroicCharacterClass.Alchemist)

}

trait AlchemistCoreEnhancementBase extends AlchemistClassEnhancementBase with CoreEnhancementBase {
  self: ClassEnhancement & AlchemistEnhancement & RequiresPointsAvailable =>
}

trait AlchemistTierOneEnhancementBase
  extends AlchemistClassEnhancementBase with Tier1EnhancementBase {
  self: ClassEnhancement & AlchemistEnhancement & RequiresPointsAvailable =>
  // Alchemist 1 implied via base Alchemist enhancement.
}

trait AlchemistTierTwoEnhancementBase
  extends AlchemistClassEnhancementBase with Tier2EnhancementBase {
  self: ClassEnhancement & AlchemistEnhancement & RequiresPointsAvailable =>
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = Seq((Alchemist, 2))
}

trait AlchemistTierThreeEnhancementBase
  extends AlchemistClassEnhancementBase with Tier3EnhancementBase {
  self: ClassEnhancement & AlchemistEnhancement & RequiresPointsAvailable =>
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = Seq((Alchemist, 3))
}

trait AlchemistTierFourEnhancementBase
  extends AlchemistClassEnhancementBase with Tier4EnhancementBase {
  self: ClassEnhancement & AlchemistEnhancement & RequiresPointsAvailable =>
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = Seq((Alchemist, 4))
}

trait AlchemistTierFiveEnhancementBase
  extends AlchemistClassEnhancementBase with Tier5EnhancementBase {
  self: ClassEnhancement & AlchemistEnhancement & RequiresPointsAvailable =>
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = Seq((Alchemist, 5))
}

trait ApothecaryCore extends AlchemistCoreEnhancementBase with Apothecary {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.Apothecary
}

trait ApothecaryTierOne extends AlchemistTierOneEnhancementBase with Apothecary {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.Apothecary
}

trait ApothecaryTierTwo extends AlchemistTierTwoEnhancementBase with Apothecary {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.Apothecary

}

trait ApothecaryTierThree extends AlchemistTierThreeEnhancementBase with Apothecary {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.Apothecary
}

trait ApothecaryTierFour extends AlchemistTierFourEnhancementBase with Apothecary {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.Apothecary
}

trait ApothecaryTierFive extends AlchemistTierFiveEnhancementBase with Apothecary {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.Apothecary
}

trait BombardierCore extends AlchemistCoreEnhancementBase with Bombardier {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.Bombardier
}

trait BombardierTierOne extends AlchemistTierOneEnhancementBase with Bombardier {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.Bombardier
}

trait BombardierTierTwo extends AlchemistTierTwoEnhancementBase with Bombardier {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.Bombardier
}

trait BombardierTierThree extends AlchemistTierThreeEnhancementBase with Bombardier {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.Bombardier
}

trait BombardierTierFour extends AlchemistTierFourEnhancementBase with Bombardier {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.Bombardier
}

trait BombardierTierFive extends AlchemistTierFiveEnhancementBase with Bombardier {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.Bombardier
}

trait VileChemistCore extends AlchemistCoreEnhancementBase with VileChemist {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.VileChemist
}

trait VileChemistTierOne extends AlchemistTierOneEnhancementBase with VileChemist {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.VileChemist
}

trait VileChemistTierTwo extends AlchemistTierTwoEnhancementBase with VileChemist {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.VileChemist
}

trait VileChemistTierThree extends AlchemistTierThreeEnhancementBase with VileChemist {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.VileChemist
}

trait VileChemistTierFour extends AlchemistTierFourEnhancementBase with VileChemist {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.VileChemist
}

trait VileChemistTierFive extends AlchemistTierFiveEnhancementBase with VileChemist {
  self: ClassEnhancement & RequiresPointsAvailable =>
  override val tree: ClassTrees = ClassTrees.VileChemist
}
