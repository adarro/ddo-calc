/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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

import io.truthencode.ddo.model.enhancement.enhancements.classbased.ApothecaryTierFour
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.SpellCriticalChancePrefix

trait SpellCriticalChancePositiveAndNegativeIV
    extends ApothecaryTierFour
    with ClassEnhancementImpl
    with SpellCriticalChancePrefix {

  override lazy val description: Option[String] =
    Some(
      """+2% chance to critically hit with Positive and Negative Spells.""".stripMargin
    )

  /**
    * Some enhancements can be taken multiple times (generally up to three)
    */
  override val ranks: Int = 1

  override protected def nameSource: String = "Positive & Negative IV".replaceRomanNumerals

  override def apCostPerRank: Int = 2

}
