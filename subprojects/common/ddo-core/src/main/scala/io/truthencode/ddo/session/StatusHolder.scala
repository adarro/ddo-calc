/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: StatusHolder.scala
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
package io.truthencode.ddo.session

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.support.naming.{DisplayName, DisplayProperties}
import io.truthencode.ddo.support.requisite.{Inclusion, Requisite}

import scala.collection.mutable

abstract class StatusHolder[T <: EnumEntry & DisplayName & DisplayProperties & Requisite &
  Inclusion] {
  type E = Enum[? <: T]
  type MappedValue = mutable.Map[String, T]
  protected lazy val source: Map[String, T] = statusEnum.values.map { v =>
    v.displayText -> v
  }.toMap
  val statusEnum: E
  val available: mutable.Map[String, T]
  val unavailable: MappedValue
  val selected: MappedValue

  def sync(requirement: Requisite*): Unit = {}

}
