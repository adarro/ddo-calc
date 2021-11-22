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
package io.truthencode.ddo.model.enhancement

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{Description, DisplayName}

import scala.collection.immutable

sealed trait TreeType extends EnumEntry with Description with DisplayName {
  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}

/**
 * Universal Trees are available to all classes / races but may require VIP / Favor
 */
trait Universal extends TreeType

/**
 * Racial trees are available to the specific character race
 */
trait Racial extends TreeType

/**
 * Class based trees are available based on having at least one level in the given class.
 */
trait ClassBased extends TreeType

/**
 * Enhancements based on Reaper Difficulties which use Survival points
 */
trait Reaper extends TreeType

object TreeType extends Enum[TreeType] {
  override def values: immutable.IndexedSeq[TreeType] = findValues

  case object Universal extends Universal
  case object Racial extends Racial
  case object ClassBased extends ClassBased
  case object Reaper extends Reaper
}
