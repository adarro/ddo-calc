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
import io.truthencode.ddo.support.SearchPrefix
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}

import scala.collection.immutable

sealed trait DestinySpheres
  extends EnumEntry with DestinySphere with DisplayName with FriendlyDisplay {

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified
   * "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "DestinySphere"

  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}

trait Arcane extends DestinySpheres
trait Divine extends DestinySpheres
trait Martial extends DestinySpheres
trait Primal extends DestinySpheres

object DestinySpheres extends Enum[DestinySpheres] with SearchPrefix {
  override def values: immutable.IndexedSeq[DestinySpheres] = findValues

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified
   * "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "Sphere"

  case object Arcane extends Arcane

  case object Divine extends Divine

  case object Martial extends Martial

  case object Primal extends Primal
}
