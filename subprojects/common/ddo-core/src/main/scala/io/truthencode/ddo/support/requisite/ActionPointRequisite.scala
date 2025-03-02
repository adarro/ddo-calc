/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ActionPointRequisite.scala
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

import io.truthencode.ddo.support.tree.Ranks

/**
 * Encapsulates the AP Cost Action Points for each rank. Specifically adds the
 * [[io.truthencode.ddo.support.requisite.ActionPointRequisite#apCostPerRank]] method
 */
sealed trait ActionPointRequisite {
  self: Requisite & Ranks =>

  /**
   * Some enhancements have multiple ranks. This is the cost for each rank. Older versions had
   * increasing costs which has been streamlined to a linear progression.
   * @return
   */
  def apCostPerRank: Int
}

trait RequiresActionPointsImpl extends MustContainImpl[Requirement] with ActionPointRequisite {
  self: Requisite & RequisiteType & Ranks =>
}

trait RequiresActionPoints
  extends ActionPointRequisite with RequiresAllOf[Requirement] with Requisite {
  self: Requisite & Ranks =>

// FIXME: Add actionpoint to req collector
  abstract override def allOf: Seq[Requirement] = super.allOf

}
