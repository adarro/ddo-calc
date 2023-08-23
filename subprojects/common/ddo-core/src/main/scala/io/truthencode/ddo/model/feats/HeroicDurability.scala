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
package io.truthencode.ddo.model.feats

import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{FeaturesImpl, HitPointAmountFeature}
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
 * This feat increases the character maximum hit points by 30.
 */
protected[feats] trait HeroicDurability
  extends FeatRequisiteImpl with Passive with FreeFeat with FeaturesImpl
  with HitPointAmountFeature {
  self: GeneralFeat =>
  override protected[this] lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.Passive)
  override protected[this] lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)
  override protected[this] lazy val hitPointCategories: Seq[effect.EffectCategories.Value] = Seq(
    effect.EffectCategories.Health)
  override protected val hitPointBonusType: BonusType = BonusType.Feat
  override protected val hitPointBonusAmount: Int = 30
}
