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
package io.truthencode.ddo.support.tree

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}

import scala.collection.immutable
sealed trait UniversalTrees
  extends EnumEntry with UniversalTree with DisplayName with FriendlyDisplay {

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified
   * "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "UniversalEnhancement"

  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}

trait Falconry extends UniversalTrees
trait HarperAgent extends UniversalTrees
trait VistaniKnifeFighter extends UniversalTrees with TreePrefix

object UniversalTrees extends Enum[UniversalTrees] {
  override def values: immutable.IndexedSeq[UniversalTrees] = findValues

  case object Falconry extends Falconry

  case object HarperAgent extends HarperAgent

  case object VistaniKnifeFighter extends VistaniKnifeFighter
}
