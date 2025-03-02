/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ImprovedHeroicDurability.scala
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

import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{FeaturesImpl, HitPointAmountFeature}
import io.truthencode.ddo.support.requisite.{
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToCharacterLevel,
  LevelRequisiteImpl
}

/**
 * This feat increases the character maximum hit points by +5. Automatically granted at levels
 * 5,10,15
 */
protected[feats] trait ImprovedHeroicDurability
  extends FeatRequisiteImpl with Passive with FreeFeat with FeaturesImpl with HitPointAmountFeature
  with LevelRequisiteImpl with GrantsToCharacterLevel {
  self: GeneralFeat =>
  override protected lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.Passive)
  override protected lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)
  override protected lazy val hitPointCategories: Seq[effect.EffectCategories.Value] = Seq(
    effect.EffectCategories.Health)
  override protected val hitPointBonusType: BonusType = BonusType.Feat
  override protected val hitPointBonusAmount: Int = 5

  override def grantToCharacterLevel: Seq[Int] = (5 to 15 by 5).toSet.toSeq
}
