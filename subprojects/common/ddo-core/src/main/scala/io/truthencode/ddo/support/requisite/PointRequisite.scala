/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
import io.truthencode.ddo.support.requisite.RequirementImplicits.{pointToReq, progressionToReq, progressionWithPointsToReq}
import io.truthencode.ddo.support.tree.TreeLike

/**
  * Represents a required amount of points spent (Action, Survival, Epic Destiny Points)
  */
sealed trait PointRequisite {
  self: Requisite =>

}

sealed trait PointInTreeRequisite extends PointRequisite {
  self: Requisite =>
  def pointsInTree: Seq[(TreeLike,SpendablePoints, Int)]
}

sealed trait PointsAvailableRequisite extends PointRequisite {
  self: Requisite =>
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
  * @note we should be able to create just one of these instead of a Race / Class / Feat etc specific one
  */
trait PointsInTreeRequisiteImpl extends MustContainImpl[Requirement] with PointInTreeRequisite {
  self: Requisite with RequisiteType =>
  override def pointsInTree: Seq[(TreeLike,SpendablePoints, Int)] = Nil
}

trait RequiresPointsInTree
    extends PointInTreeRequisite
    with RequiresAllOf[Requirement]
    with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    pointsInTree collect progressionWithPointsToReq
  }
}

trait PointsAvailableRequisiteImpl
    extends MustContainImpl[Requirement]
    with PointsAvailableRequisite {
  self: Requisite with RequisiteType =>
  override def pointsAvailable: Seq[(SpendablePoints, Int)] = Nil
}

trait RequiresPointsAvailable
    extends PointsAvailableRequisite
    with RequiresAllOf[Requirement]
    with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    pointsAvailable collect pointToReq
  }
}
