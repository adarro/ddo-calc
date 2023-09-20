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
package io.truthencode.ddo.model.enhancement.enhancements.classbased

import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.enhancement._
import io.truthencode.ddo.model.enhancement.enhancements.{expanders, ClassEnhancement}
import io.truthencode.ddo.support.Elvis._
import io.truthencode.ddo.support.points.SpendablePoints
import io.truthencode.ddo.support.points.SpendablePoints.ActionPoints
import io.truthencode.ddo.support.requisite._
import io.truthencode.ddo.support.tree.TreeLike

/**
 * Convenience trait used to group class / race enhancement tier common information
 */
trait ClassEnhancementBase {
  self: ClassEnhancement
    with PointInTreeRequisite with RequiresPointsAvailable with ActionPointRequisite with Tier =>
  def validClasses: Seq[HeroicCharacterClass]

  def singleClass: Option[HeroicCharacterClass] = isSingleClass ? validClasses.headOption | None

  def isSingleClass: Boolean = validClasses.size.equals(1)

  override def pointsAvailable: Seq[(SpendablePoints, Int)] = Seq((ActionPoints, apCostPerRank))
}

trait CoreEnhancementBase
  extends ClassEnhancementBase with PointsInTreeRequisiteImpl with RequiresPointsInTree
  with RequiresPointsAvailable with RequiresActionPoints with Core {
  self: ClassEnhancement =>
  // tree progression varies with Core and should be overridden by implementing class.

}

trait Tier1EnhancementBase
  extends ClassEnhancementBase with PointsInTreeRequisiteImpl with RequiresPointsInTree
  with RequiresPointsAvailable with RequiresActionPoints with Tier1 {
  self: ClassEnhancement =>
  override def progressionInTree: Seq[(TreeLike, SpendablePoints, Int)] = Seq((tree, 1))
}

trait Tier2EnhancementBase
  extends ClassEnhancementBase with PointsInTreeRequisiteImpl with RequiresPointsInTree
  with RequiresPointsAvailable with RequiresActionPoints with Tier2 {
  self: ClassEnhancement with RequiresPointsAvailable =>
  override def progressionInTree: Seq[(TreeLike, SpendablePoints, Int)] = Seq((tree, 5))
}

trait Tier3EnhancementBase
  extends ClassEnhancementBase with PointsInTreeRequisiteImpl with RequiresPointsInTree
  with RequiresPointsAvailable with RequiresActionPoints with Tier3 {
  self: ClassEnhancement with RequiresPointsAvailable =>
  override def progressionInTree: Seq[(TreeLike, SpendablePoints, Int)] = Seq((tree, 10))
}

trait Tier4EnhancementBase
  extends ClassEnhancementBase with PointsInTreeRequisiteImpl with RequiresPointsInTree
  with RequiresPointsAvailable with RequiresActionPoints with Tier4 {
  self: ClassEnhancement with RequiresPointsAvailable =>
  override def progressionInTree: Seq[(TreeLike, SpendablePoints, Int)] = Seq((tree, 20))
}

trait Tier5EnhancementBase
  extends ClassEnhancementBase with PointsInTreeRequisiteImpl with RequiresPointsInTree
  with RequiresPointsAvailable with RequiresActionPoints with Tier5 {
  self: ClassEnhancement with RequiresPointsAvailable =>
  override def progressionInTree: Seq[(TreeLike, SpendablePoints, Int)] = Seq((tree, 30))
}
