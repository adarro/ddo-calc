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
import io.truthencode.ddo.model.effect.features.{FeaturesImpl, GrantAbilityFeature}
import io.truthencode.ddo.model.misc.DefaultCoolDown
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
 * Icon Feat Improved Sunder.png [[https://ddowiki.com/page/Improved_Sunder Improved Sunder]] Active
 *   - Special Attack This melee special attack results in a -5 AC penalty and -10% fortification to
 *     the target for 24 seconds, if the target fails a Fortitude save (DC 14 + Str mod). Whether
 *     successful or unsuccessful, this attack will also reduce an enemy's Fortitude saves by 3,
 *     stacking up to 5 times. Some creatures may be immune to the sunder effect.
 *
 * Sunder Power Attack
 */
protected[feats] trait ImprovedSunder
  extends FeatRequisiteImpl with ActiveFeat with RequiresAllOfFeat with FighterBonusFeat
  with Tactical with DefaultCoolDown with MartialArtsFeat with FeaturesImpl
  with GrantAbilityFeature {
  self: GeneralFeat =>
  override lazy val grantedAbility: ActiveAbilities = ActiveAbilities.ImprovedSunder
  override protected[this] val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.AtWill)
  override protected[this] val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.OnCoolDown)
  override protected[this] val grantAbilityCategories: Seq[effect.EffectCategories.Value] = Seq(
    effect.EffectCategories.Ability)
  override val abilityId: String = "Sunder"
  override val description: String =
    "This melee special attack, when successful, results in a -5 AC penalty to the target for 24 seconds"
  override val allOfFeats = List(GeneralFeat.Sunder, GeneralFeat.PowerAttack)
  override val grantBonusType: BonusType = BonusType.Feat
}
