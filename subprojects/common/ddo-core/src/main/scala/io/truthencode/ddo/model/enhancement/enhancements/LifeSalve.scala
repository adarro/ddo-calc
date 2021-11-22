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
package io.truthencode.ddo.model.enhancement.enhancements

import io.truthencode.ddo.model.enhancement.enhancements.classbased.ApothecaryTierTwo

trait LifeSalve extends ApothecaryTierTwo with ClassEnhancementImpl {

  override lazy val description: Option[String] = Some(
    """ Training this Enhancement also grants you a similar ability Death Salve. Target player, hireling, or pet takes 100% base healing from Positive spells for the next 3 minutes (or until target dies.) Has no effect on NPC allies or allies that already take 100% base Positive healing. This overrides their innate base healing from Positive spells for the duration. Shares a cooldown with Death Salve and Converter. Note: cannot be used on self."""
  )

  override def apCostPerRank: Int = 2

  /**
   * Some enhancements can be taken multiple times (generally up to three)
   */
  override val ranks: Int = 1

}
