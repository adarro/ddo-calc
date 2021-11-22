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
package io.truthencode.ddo.model.spells

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

sealed trait SavingThrowResult extends EnumEntry

object SavingThrowResult extends Enum[SavingThrowResult] {

  override def values: immutable.IndexedSeq[SavingThrowResult] = findValues

  /**
   * No Saving throw is allowed.
   */
  case object None extends SavingThrowResult

  /**
   * On successful save, some partial effect or percentage is avoided.
   */
  case object Partial extends SavingThrowResult

  /**
   * On successful save, half damage or duration is received.
   */
  case object Half extends SavingThrowResult

  /**
   * On successful save, No damage or ill effects occur.
   */
  case object Negates extends SavingThrowResult

}
