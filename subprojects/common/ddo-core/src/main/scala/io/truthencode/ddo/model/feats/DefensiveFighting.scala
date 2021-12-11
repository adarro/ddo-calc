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
import io.truthencode.ddo.model.abilities.ActiveAbilities
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{
  ArmorClassPercentFeature,
  FeaturesImpl,
  GrantAbilityFeature,
  HitChancePercentFeature
}

import java.time.Duration
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
 * [[https://ddowiki.com/page/Defensive_Fighting Defensive Fighting]] While using Defensive Fighting mode, you gain a 5%
 * bonus to AC and -5% penalty to-hit. Casting a spell ends this mode.
 * @note
 *   Entering this stance will cause you to end any other stances you may have active, and also dispel any rage or
 *   recklessness effects currently present on your character.
 */
protected[feats] trait DefensiveFighting
  extends FeatRequisiteImpl with ActiveFeat with DefensiveCombatStance with FreeFeat with FeaturesImpl
  with GrantAbilityFeature with ArmorClassPercentFeature with HitChancePercentFeature {
  self: GeneralFeat =>
  override val grantBonusType: BonusType = BonusType.Feat
  override val grantedAbility: ActiveAbilities = ActiveAbilities.DefensiveFighting

  lazy override protected[this] val categories: Seq[effect.EffectCategories.Value] =
    Seq(effect.EffectCategories.Stance, effect.EffectCategories.MissChance, effect.EffectCategories.HitChance)
  override val abilityId: String = "DefensiveFighting"
  override val description: String = "A stance that increases your defense"
  lazy override protected[this] val triggerOn: TriggerEvent = TriggerEvent.OnStance
  lazy override protected[this] val triggerOff: TriggerEvent = TriggerEvent.OnToggle
  lazy override protected[this] val hitChanceCategories: Seq[effect.EffectCategories.Value] = Seq(
    effect.EffectCategories.MissChance)

  /**
   * @note
   *   Set to No Cooldown but likely has some default minimal one
   * @return
   *   Cool Down Duration (Currently None)
   */
  override def coolDown: Option[Duration] = None

  override protected val armorBonusAmount: Int = 5
  override protected val armorBonusType: BonusType = BonusType.Feat
  override protected val hitChanceBonusAmount: Int = -5
  override protected val hitChanceBonusType: BonusType = BonusType.Feat
}
