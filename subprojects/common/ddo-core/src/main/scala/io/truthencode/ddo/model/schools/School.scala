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
package io.truthencode.ddo.model.schools

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.support.SearchPrefix
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}

import scala.collection.immutable.IndexedSeq

/**
 * Represents one of the eight schools of magic.
 */
sealed trait School extends EnumEntry with DisplayName with FriendlyDisplay {

  /**
   * Sets or maps the source text for the DisplayName.
   *
   * @return
   *   Source text.
   */
  override protected def nameSource: String = entryName.splitByCase
}

object School extends Enum[School] with SearchPrefix {

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified
   * "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "School"

  override def values: IndexedSeq[School] = findValues

  case object Abjuration extends School

  case object Conjuration extends School

  case object Divination extends School

  case object Enchantment extends School

  case object Evocation extends School

  case object Illusion extends School

  case object Necromancy extends School

  case object Transmutation extends School
}
