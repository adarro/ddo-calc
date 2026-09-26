/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ProgressionRequisite.scala
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

import io.truthencode.ddo.support.requisite.RequirementImplicits.progressionToReq
import io.truthencode.ddo.support.tree.TreeLike

/**
 * Represents a required amount of points spent (Action, Survival, Epic Destiny Points)
 */
sealed trait ProgressionRequisite {
  self: Requisite =>

}

sealed trait ProgressionInTreeRequisite extends ProgressionRequisite {
  self: Requisite =>
  def pointsInTree: Seq[(TreeLike, Int)]
}

// /**
// * Represents total points spent
// */
// sealed trait PointsSpentRequisite extends ProgressionRequisite {
//  self: Requisite =>
//  def pointsSpent: Seq[(Points, Int)]
// }

/**
 * Base Stackable trait implementation used to initialize when no other has been used.
 * @note
 *   we should be able to create just one of these instead of a Race / Class / Feat etc specific one
 */
trait ProgressionRequisiteImpl
  extends MustContainImpl[Requirement] with ProgressionInTreeRequisite {
  self: Requisite & RequisiteType =>
  override def pointsInTree: Seq[(TreeLike, Int)] = Nil
}

trait RequiresTreeProgression
  extends ProgressionInTreeRequisite with RequiresAllOf[Requirement] with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    pointsInTree.collect(progressionToReq)
  }
}
