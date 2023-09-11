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
package io.truthencode.ddo.support.matching

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

/**
 * Enumeration useful in determining case comparison and or manipulation.
 */
sealed trait CaseMatchOption extends EnumEntry

/**
 * Holds strategies for case (in)sensitive matching against strings.
 */
object CaseMatchOption extends Enum[CaseMatchOption] {

  override def values: immutable.IndexedSeq[CaseMatchOption] = findValues

  /**
   * Compares or manipulates based on UpperCase
   */
  case object UpperCase extends CaseMatchOption

  /**
   * Compares or manipulates based on LowerCase
   */
  case object LowerCase extends CaseMatchOption

  /**
   * Compares or manipulates ignoring or preserving case
   */
  case object IgnoreCase extends CaseMatchOption

}
