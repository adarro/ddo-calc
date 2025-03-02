/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Toughness.scala
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
import io.truthencode.ddo.model.effect.features.{FeaturesImpl, HitPointPerLevelAmountFeature}
import io.truthencode.ddo.providers.SimpleValueProvider
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
 * Icon Feat Toughness.png Toughness Passive This feat Increases your hit points by +3 at first
 * level, and adds +1 additional hit point for each additional level. This feat can be taken
 * multiple times, so that the effects stack. * None
 * @note
 *   The hitpoint calculation attempts to use an Implicit [[SimpleValueProvider]]. It will create
 *   and use a default [[ToughnessHitPointsPerLevelProvider]] if no implicit value is found.
 *   Although we should probably type the implicit more specifically here
 */
protected[feats] trait Toughness
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with Passive with FreeFeat
  with MartialArtsFeat with FeaturesImpl with HitPointPerLevelAmountFeature {
  self: GeneralFeat =>
  override protected lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.Passive)
  override protected lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)
  override protected lazy val hitPointPerLevelCategories: Seq[effect.EffectCategories.Value] =
    Seq(effect.EffectCategories.Health)
  override protected val hitPointBonusType: BonusType = BonusType.Feat
  override protected val hitPointsPerLevel: Int = 0

  def provider: SimpleValueProvider[Int] = new ToughnessHitPointsPerLevelProvider()

  /**
   * 3 at first level, and adds +1 for each other level. Technically
   *
   * @example
   *   val currentLevel = 3 calculateHitPointsPerLevel(3) // 5 // Officially this would be <br />
   *   Seq(3) ++ (2 to levelCap).map(_ => 1).sum // Effectively this is currentLevel + 2
   * @param currentLevel
   *   Current Character Level
   * @return
   *   Additional Hit Points
   */
  def calculateHitPointsPerLevel(currentLevel: Int): Int = { currentLevel + 2 }

  override def calculate: Int => Int = doMath

  def doMath[Int](implicit
    provider: SimpleValueProvider[Int] = provider.asInstanceOf[SimpleValueProvider[Int]])
    : Int => Int = {
    provider.createValue
  }
}
