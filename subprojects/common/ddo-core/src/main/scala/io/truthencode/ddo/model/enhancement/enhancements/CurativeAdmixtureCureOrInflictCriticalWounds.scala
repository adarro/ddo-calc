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

import io.truthencode.ddo.model.enhancement.enhancements.classbased.ApothecaryTierFive
import io.truthencode.ddo.support.StringUtils.Extensions

trait CurativeAdmixtureCureOrInflictCriticalWounds
  extends ApothecaryTierFive with ClassEnhancementImpl with CurativeAdmixtureBase {

  override lazy val description: Option[String] =
    Some(
      """Curative Admixture: Cure Critical Wounds Or Curative Admixture: Inflict Critical Wounds
        |Metamagic: Empower, Empower Heal, Maximize, Intensify Spell Point Cost: 45 Cooldown 12 seconds Primer Element: Gildleaf
        |
        |Infuses a Cure/Inflict Critical Wounds potion with magical energy, hurling it towards your target in an arc. The potion explodes in a small AOE that deals 4d6+8 plus 1 per caster level (maximum caster level 20) positive/negative energy damage to nearby living allies and enemy undead.""".stripMargin
    )

  /**
   * Some enhancements can be taken multiple times (generally up to three)
   */
  override val ranks: Int = 1

  override def displayText: String = displaySource.lowerCaseNoise
  override protected def nameSource: String = "Cure Or Inflict Critical Wounds"

  override def apCostPerRank: Int = 1

}
