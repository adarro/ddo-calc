/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: PointRequisite.scala
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
package io.truthencode.ddo.support.requisite

import io.truthencode.ddo.support.points.SpendablePoints
import io.truthencode.ddo.support.requisite.RequirementImplicits.{
  pointToReq,
  progressionWithPointsToReq
}
import io.truthencode.ddo.support.tree.TreeLike

/**
 * Represents a required amount of points spent (Action, Survival, Epic Destiny Points)
 */
sealed trait PointRequisite {
  self: Requisite =>

}

/**
 * Represents a progression in a given area such as 20 action points spent in a particular tree to
 * qualify for an enhancement.
 */
sealed trait PointInTreeRequisite extends PointRequisite {
  self: Requisite =>

  /**
   * Minimum points already invested
   * @return
   *   Minimum points of the given type spent in the given tree.
   */
  def progressionInTree: Seq[(TreeLike, SpendablePoints, Int)]
}

sealed trait PointsAvailableRequisite extends PointRequisite {
  self: Requisite =>

  /**
   * Denotes the type and amount of points required to be available to acquire the given Enhancement
   * @note
   *   this may need to become a stackable trait to support multiple types.
   * @return
   *   Seq of values.
   */
  def pointsAvailable: Seq[(SpendablePoints, Int)]
}

// /**
// * Represents total points spent
// */
// sealed trait PointsSpentRequisite extends PointRequisite {
//  self: Requisite =>
//  def pointsSpent: Seq[(Points, Int)]
// }

/**
 * Base Stackable trait implementation used to initialize when no other has been used.
 * @note
 *   we should be able to create just one of these instead of a Race / Class / Feat etc specific one
 */
trait PointsInTreeRequisiteImpl extends MustContainImpl[Requirement] with PointInTreeRequisite {
  self: Requisite & RequisiteType =>
  override def progressionInTree: Seq[(TreeLike, SpendablePoints, Int)] = Nil
}

trait RequiresPointsInTree
  extends PointInTreeRequisite with RequiresAllOf[Requirement] with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    progressionInTree.collect(progressionWithPointsToReq)
  }
}

trait PointsAvailableRequisiteImpl
  extends MustContainImpl[Requirement] with PointsAvailableRequisite {
  self: Requisite & RequisiteType =>
  override def pointsAvailable: Seq[(SpendablePoints, Int)] = Nil
}

/**
 * Denotes the amount of points needed (or that must be available) to acquire this Enhancement. This
 * is less specific than [[RequiresPointsInTree]], which specifics the amount of points spent in a
 * specific tree.
 */
trait RequiresPointsAvailable
  extends PointsAvailableRequisite with RequiresAllOf[Requirement] with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    pointsAvailable.collect(pointToReq)
  }
}
