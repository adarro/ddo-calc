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
package io.truthencode.ddo.support.requisite

import io.truthencode.ddo.support.requisite.ValueWithRequirements.sortWithChaos

case class SemiOrderedRequirements(valueWithRequirements: ValueWithRequirements*) {

  /**
   * Required Requisites unless specifically granted by an overriding rule.
   * @return
   *   Required Requisites
   */
  def prerequisites: Seq[ValueWithRequirements] = {
    valueWithRequirements.filter { v => v.groupKey.equals(requiredGroupKey) }
  }

  def displayPrerequisites: Seq[String] = {
    for {
      p <- prerequisites
      r: Requirement <- p.req.sortWith((x, y) => sortWithChaos(x, y))
    } yield r.displayText
  }

  /**
   * Can select based as a bonus when this criteria is met.
   * @return
   *   Bonus Selections
   */
  def bonusSelectable: Seq[ValueWithRequirements] = {
    valueWithRequirements.filter { v => v.groupKey.equals("BonusSelection") }
  }

  def displayBonusSelections: Seq[String] = {
    for {
      p <- bonusSelectable.sorted
      r <- p.req.sortWith((x, y) => sortWithChaos(x, y))
    } yield r.displayText
  }

  /**
   * Includes any automatically granted requirements
   * @return
   *   Automatically granted requirements
   */
  def grantedBy: Seq[ValueWithRequirements] = {
    valueWithRequirements.filter { v => v.groupKey.contains("Grant") }
  }

  def displayGranted: Seq[String] = {
    for {
      p <- grantedBy.sorted
      r <- p.req.sortWith((x, y) => sortWithChaos(x, y))
    } yield r.displayText
  }

}
