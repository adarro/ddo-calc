/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: AbilitySelector.scala
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
package io.truthencode.ddo.model.enhancement.enhancements

import io.truthencode.ddo.model.enhancement.Tier
import io.truthencode.ddo.support.naming.RomanNumeralAffix
import io.truthencode.ddo.support.requisite.ActionPointRequisite

/**
 * Basic support for adding +1 of some stat in an enhancement.
 * @note
 *   trait manipulates diplay text so mix in AFTER other text manipulations to ensure Roman Numeral
 *   / key integrity.
 */
trait AbilitySelector extends AbilityScoreEnhancement with RomanNumeralAffix {
  self: ClassEnhancement & Tier & ActionPointRequisite =>

  /**
   * Some enhancements can be taken multiple times (generally up to three)
   */
  override val ranks: Int = 1

  override def apCostPerRank: Int = 2

  override protected def nameSource: String = "Ability"

}
