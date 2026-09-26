/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Mobility.scala
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
import io.truthencode.ddo.model.effect.features.{
  ArmorClassAmountFeature,
  DodgeChanceFeature,
  FeaturesImpl,
  MaxDexBonusFeature
}
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
 * Icon Feat Mobility.png Mobility Passive Increases the maximum dexterity bonus permitted by armor
 * and tower shields by 2, and adds a +4 bonus to Armor Class while the character is tumbling. You
 * will also gain a 2% dodge bonus. Dodge
 */
trait Mobility
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with Passive with RequiresAllOfFeat
  with MartialArtsFeat with FighterBonusFeat with AlchemistBonusFeat with FeaturesImpl
  with DodgeChanceFeature with MaxDexBonusFeature with ArmorClassAmountFeature {
  self: GeneralFeat =>
  override protected lazy val dodgeCategories: Seq[effect.EffectCategories.Value] = Seq(
    effect.EffectCategories.MissChance)
  override protected lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.Passive)
  override protected lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)
  override protected lazy val acTriggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.OnTumble)
  override protected lazy val acTriggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.WhileOn)
  override protected lazy val armorBonusType: BonusType = BonusType.Feat
  override protected val armorBonusAmount: Int = 4
  override val mdbBonusType: BonusType = BonusType.Feat
  override val mdbAmount: Int = 2
  override protected val mdbCategories: Seq[effect.EffectCategories.Value] = Seq(
    effect.EffectCategories.MissChance)
  override val dodgeBonusType: BonusType = BonusType.Feat
  override val dodgeBonusAmount: Int = 2

  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.Dodge)
}
