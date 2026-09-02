/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2026
 *
 * Author: Andre White.
 * FILE: AdjustmentType.scala
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
package io.truthencode.ddo.support.numbers

import enumeratum.{Enum, EnumEntry}

sealed trait AdjustmentType extends EnumEntry

/**
 * Flags the adjustment to annotate the type of change affected.
 */
object AdjustmentType extends Enum[AdjustmentType] {
  override def values: IndexedSeq[AdjustmentType] = findValues

  /**
   * Changes (sets) the base value
   */
  case object Base extends AdjustmentType

  /**
   * Adjusts the value by a given number such as Magical Sheltering + 2
   */
  case object Value extends AdjustmentType

  /**
   * Adjusts the value by a percentage such as Max Hit Points + 5%
   */
  case object Percent extends AdjustmentType

}
