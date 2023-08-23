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
 * Flag used to denote inclusion logic
 */
sealed trait Inclusion extends EnumEntry {
  def inclusionType: String
}

trait AllOf extends Inclusion {
  override def inclusionType: String = "All Of"
}

trait AnyOf extends Inclusion {
  override def inclusionType: String = "Any Of"
}

trait NoneOf extends Inclusion {
  override def inclusionType: String = "None Of"
}

object Inclusion extends Enum[Inclusion] {
  case object AllOf extends AllOf
  case object AnyOf extends AnyOf
  case object NoneOf extends NoneOf
  override def values: IndexedSeq[Inclusion] = findValues
}
