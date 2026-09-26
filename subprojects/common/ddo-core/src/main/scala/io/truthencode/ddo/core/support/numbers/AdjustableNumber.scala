/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2026
 *
 * Author: Andre White.
 * FILE: AdjustableNumber.scala
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
package io.truthencode.ddo.core.support.numbers

import io.truthencode.ddo.core.{NonStacking, StacksWithAny, StacksWithUnique}

trait AdjustableNumber {

  val baseValue: Int

  val basePercent: Int

  def adjust(adjustment: Adjustment*): (Int, Int) = {

    val bases = adjustment.filter { _.kind == AdjustmentType.Base }
    val amt = adjustment.filter { _.kind == AdjustmentType.Value }
    val pct = adjustment.filter { _.kind == AdjustmentType.Percent }
// TODO: Implement
    val r: (Int, Int) = ???
    r
  }
}

object AdjustableNumber {
  val fnStackAny: PartialFunction[Adjustment, Adjustment] = {
    case x if x.bonusType.isInstanceOf[StacksWithAny] =>
      x
  }

  /**
   * Stacks with others but not the same. I.e., shield bonus stacks with any but other shield
   * bonuses
   */
  val fnStackNone: PartialFunction[Adjustment, Adjustment] = {
    case x if x.bonusType.isInstanceOf[NonStacking] =>
      x
  }

  /**
   * Stacks with others but not the same. i.e., shield bonus stacks with any but other shield
   * bonuses
   */
  val fnStackDifferent: PartialFunction[Adjustment, Adjustment] = {
    case x if x.bonusType.isInstanceOf[StacksWithUnique] =>
      x
  }
  def calculateSingleType(adjustment: Adjustment*): Unit = {
    val anyStacks = adjustment.collect(fnStackAny)
    val nonStacking = adjustment.collect(fnStackNone)
    val diffStack = adjustment.collect(fnStackDifferent)
    val fl = anyStacks.foldLeft(0)((x, y) => x + y.value)

  }
}
