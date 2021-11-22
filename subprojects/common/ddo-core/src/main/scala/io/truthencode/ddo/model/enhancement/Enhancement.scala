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
import io.truthencode.ddo.support.requisite.RaceRequisite

import scala.collection.immutable

/**
 * Enhancements are acquired via the Skill Tree by spending action points.
 */
protected[ddo] trait Enhancement extends EnumEntry with Description with DisplayName {
  self: TreeType with Tier =>

  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase

}

/**
 * Available to all races / classes but may need to be purchased via favor or VIP
 */
trait UniversalEnhancement extends Enhancement with Universal {
  self: Tier =>
}

/**
 * Available to a particular race.
 */
trait RacialEnhancement extends Enhancement with Racial {
  self: Tier with RaceRequisite =>
}

object Enhancement extends Enum[Enhancement] {
  override def values: immutable.IndexedSeq[Enhancement] = ??? // findValues
  // Alchemist
  // Core

}
