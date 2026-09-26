/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Diehard.scala
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
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Ranger
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{FeaturesImpl, UnconsciousRecoveryFeature}
import io.truthencode.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
 * Icon Feat Diehard.png Diehard Passive You automatically stabilize when incapacitated.
 */
protected[feats] trait Diehard
  extends FeatRequisiteImpl with ClassRequisiteImpl with BonusSelectableToClassFeatImpl with Passive
  with FreeFeat with GrantsToClass with MartialArtsFeat with FeaturesImpl
  with UnconsciousRecoveryFeature {
  self: GeneralFeat =>
  override protected lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.OnUnconscious)
  override protected lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)
  override protected lazy val unconsciousRecoveryCategories: Seq[effect.EffectCategories.Value] =
    Seq(effect.EffectCategories.Ability, effect.EffectCategories.Recovery)
  override val autoRecoveryBonus: BonusType = BonusType.Feat
  override val isAutoRecovery: Boolean = true

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = List((Ranger, 3))
}
