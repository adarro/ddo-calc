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

import scala.collection.immutable

/**
 * Created by adarr on 4/11/2017.
 */
sealed trait RequirementOption extends EnumEntry {}

object RequirementOption extends Enum[RequirementOption] {

  /**
   * Automatically granted without the need to acquire additional combinations.
   *
   * @note
   *   minimum level still applies. This is used mainly for quickly determining if a given feat / skill etc are
   *   automatically granted upon character creation or reaching a level as opposed to explicitly needing to purchase.
   */
  case object AutoGrant extends RequirementOption

  /**
   * This Feat / Skill etc can be purchased but contains additional restrictions such as being a certain race AND a
   * certain class.
   */
  case object SelectableWithRestriction extends RequirementOption

  /**
   * This generally applies to Bonus Feats which are generally tied to class or race.
   */
  case object SelectableAsBonus extends RequirementOption

  /**
   * Can be purchased / selected without excess restrictions. Examples include the Toughness Feat.
   */
  case object Available extends RequirementOption

  override def values: immutable.IndexedSeq[RequirementOption] = findValues
}
