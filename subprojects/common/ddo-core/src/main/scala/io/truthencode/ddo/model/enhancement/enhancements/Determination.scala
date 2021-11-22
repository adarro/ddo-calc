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

import io.truthencode.ddo.model.enhancement.enhancements.classbased.ApothecaryCore
import io.truthencode.ddo.support.points.SpendablePoints
import io.truthencode.ddo.support.tree.{ClassTrees, TreeLike}

trait Determination extends ApothecaryCore with ClassEnhancementImpl {
  // Will Save +1
  // override val tree: ClassTrees = ClassTrees.Apothecary
  override lazy val description: Option[String] = Some("+1 Will Save")
  override def apCostPerRank: Int = 1

  /**
   * Some enhancements can be taken multiple times (generally up to three)
   */
  override val ranks: Int = 1

  // override def allOfClass: Seq[(HeroicCharacterClass, Int)] = Seq((Alchemist, 1))

  override def progressionInTree: Seq[(TreeLike, SpendablePoints, Int)] = Seq((tree, 0))
}
