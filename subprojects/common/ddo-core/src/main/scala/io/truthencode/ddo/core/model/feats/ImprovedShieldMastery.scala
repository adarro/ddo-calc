/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ImprovedShieldMastery.scala
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
package io.truthencode.ddo.model.feats

import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresBaB}

/**
 * Icon Feat Improved Shield Mastery.png Improved Shield Mastery Passive You are exceptionally
 * skilled with the use of a shield, and your physical resistance is increased by an additional 5
 * when using a buckler or small shield, 10 when using a large shield, or 15 when using a tower
 * shield. Your Doublestrike chance while using a shield is increased by 5% to a total of 8%. Add +3
 * Melee Power(for a total of +6) * Shield Mastery Base Attack Bonus +8
 */
protected[feats] trait ImprovedShieldMastery
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with Passive with RequiresAllOfFeat
  with RequiresBaB with FighterBonusFeat {
  self: GeneralFeat =>
  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.ShieldMastery)

  override def requiresBaB: Int = 8
}
