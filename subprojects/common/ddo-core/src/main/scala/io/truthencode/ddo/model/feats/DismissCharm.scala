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

import io.truthencode.ddo.activation.AtWillEvent
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.abilities.ActiveAbilities
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{FeaturesImpl, GrantAbilityFeature}
import io.truthencode.ddo.model.misc.DefaultCoolDown
import io.truthencode.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
 * [[https://ddowiki.com/page/Dismiss_Charm Dismiss Charm]] Activate this short-ranged ability while
 * targeting a charmed, commanded, controlled, or dominated enemy that is under your control to
 * dispel the controlling effect.
 *
 * @note
 *   As of Update 26 all classes now receive this feat at level 1
 */
protected[feats] trait DismissCharm
  extends FeatRequisiteImpl with ActiveFeat with AtWillEvent with DefaultCoolDown with FreeFeat
  with ClassRequisiteImpl with GrantsToClass with FeaturesImpl with GrantAbilityFeature {
  self: GeneralFeat =>

  override lazy val grantedAbility: ActiveAbilities = ActiveAbilities.DismissCharm
  override val grantBonusType: BonusType = BonusType.Feat
  override protected[this] val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.OnToggle)
  override protected[this] val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.OnToggle)
  override protected[this] val grantAbilityCategories: Seq[effect.EffectCategories.Value] = Seq(
    effect.EffectCategories.Ability)
  override val abilityId: String = "DismissCharm"
  override val description: String = "Creates ability to dismiss some charmed mobs."

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    HeroicCharacterClass.values.map((_, 1))
}
