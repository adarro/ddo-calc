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
import io.truthencode.ddo.model.effect.features.{
  ArmorClassAmountFeature,
  DodgeChanceFeature,
  FeaturesImpl,
  MaxDexBonusFeature
}
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
 * Icon Feat Mobility.png Mobility Passive Increases the maximum dexterity bonus permitted by armor and tower shields by
 * 2, and adds a +4 bonus to Armor Class while the character is tumbling. You will also gain a 2% dodge bonus. Dodge
 */
trait Mobility
  extends FeatRequisiteImpl with Passive with RequiresAllOfFeat with MartialArtsFeat with FighterBonusFeat
  with AlchemistBonusFeat with FeaturesImpl with DodgeChanceFeature with MaxDexBonusFeature
  with ArmorClassAmountFeature {
  self: GeneralFeat =>
  override protected val armorBonusType: BonusType = BonusType.Feat
  override protected val armorBonusAmount: Int = 4
  override val mdbBonusType: BonusType = BonusType.Feat
  lazy override protected[this] val categories: Seq[effect.EffectCategories.Value] = Seq(
    effect.EffectCategories.MissChance)
  override val mdbAmount: Int = 2
  lazy override protected[this] val triggerOn: TriggerEvent = TriggerEvent.Passive
  lazy override protected[this] val triggerOff: TriggerEvent = TriggerEvent.Never
  lazy override protected[this] val acTriggerOn: TriggerEvent = TriggerEvent.OnTumble
  lazy override protected[this] val acTriggerOff: TriggerEvent = TriggerEvent.WhileOn
  override val dodgeBonusType: BonusType = BonusType.Feat
  override val dodgeBonusAmount: Int = 2

  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.Dodge)
}
