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
package io.truthencode.ddo.support.numbers

import enumeratum.{Enum, EnumEntry}

sealed trait Numbers extends EnumEntry

/**
 * Flags / case classes used for evaluating and typing numbers for saves / difficulty checks /
 * magnitude etc.
 */
object Numbers extends Enum[Numbers] {
  override def values: IndexedSeq[Numbers] = findValues

  /**
   * Used to amplify a given value. // TODO: Implement Magnitude then change this to a case class
   * applying [[io.truthencode.ddo.model.effect.Magnitude]]
   */
  case object Magnitude extends Numbers

  /**
   * Will be used for flagging / calculating saves / difficult checks.
   */
  case object DifficultyCheck extends Numbers
}
