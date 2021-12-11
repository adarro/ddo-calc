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

import java.time.Duration
import io.truthencode.ddo.activation.AtWillEvent
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.abilities.ActiveAbilities
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{FeaturesImpl, GrantAbilityFeature}
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
 * Icon Feat Slicing Blow.png [[https://ddowiki.com/page/Slicing_Blow Slicing Blow]] Active - Special Attack Using this
 * attack, you deal 1 point of Constitution damage to your target and deal 1d4 additional damage 2 seconds later as the
 * target bleeds. The target suffers an additional round of bleeding for every 3 character levels, up to a max of 6 at
 * level 15. Some creatures may be immune to the bleeding effect.
 *
 * None
 */
protected[feats] trait SlicingBlow
  extends FeatRequisiteImpl with ActiveFeat with AtWillEvent with FreeFeat with FighterBonusFeat with FeaturesImpl
  with GrantAbilityFeature {
  self: GeneralFeat =>
  // TODO: Add Bleed Effect to slicing blow
  override protected[this] val triggerOn: TriggerEvent = TriggerEvent.SpecialAttack
  override protected[this] val triggerOff: TriggerEvent = TriggerEvent.OnCoolDown
  override protected[this] val categories: Seq[effect.EffectCategories.Value] =
    Seq(effect.EffectCategories.Ability, effect.EffectCategories.SpecialAttack)
  override val abilityId: String = "SlicingBlow"
  override val description: String =
    "Special Attack Using this attack, you deal 1 point of Constitution damage to your target and deal 1d4 additional damage 2 seconds later as the target bleeds"

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(15))

  override val grantBonusType: BonusType = BonusType.Feat
  override val grantedAbility: ActiveAbilities = ActiveAbilities.SlicingBlow
}
