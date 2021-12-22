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
package io.truthencode.ddo.support.requisite

import enumeratum.{Enum, EnumEntry}

/**
 * Flag used to determine whether a given expression is granting, requiring or prohibiting.
 */
sealed trait RequisiteType extends EnumEntry {
  def requisiteType: String
}

trait Grant extends RequisiteType {
  override def requisiteType: String = "Grants"
}

trait Prohibit extends RequisiteType {
  override def requisiteType: String = "Prohibits"
}

trait Require extends RequisiteType {
  override def requisiteType: String = "Requires"
}

object RequisiteType extends Enum[RequisiteType] {
  case object Grant extends Grant
  case object Prohibit extends Prohibit
  case object Require extends Require
  override def values: IndexedSeq[RequisiteType] = findValues
}
