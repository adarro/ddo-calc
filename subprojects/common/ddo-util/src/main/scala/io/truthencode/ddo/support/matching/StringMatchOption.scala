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
 * Enumeration useful in determining whether to apply a condition to a character or full word.
 */
sealed trait StringMatchOption extends EnumEntry

/**
 * Holds character matching strategies when parsing strings.
 */
object StringMatchOption extends Enum[StringMatchOption] {

  override def values: immutable.IndexedSeq[StringMatchOption] = findValues

  /**
   * Applies to First Character
   */
  case object FirstCharacter extends StringMatchOption

  /**
   * Applies to each character in word.
   */
  case object FullWord extends StringMatchOption

  /**
   * Applies to each character
   */
  case object EachCharacter extends StringMatchOption
}
