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
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{FeaturesImpl, GrantAbilityFeature}
import io.truthencode.ddo.support.requisite.{ClassRequisiteImpl, FeatRequisiteImpl, RequiresAllOfClass}

trait WhirlingSteelStrike
  extends FeatRequisiteImpl with ClassRequisiteImpl with Passive with RequiresAllOfClass with FighterBonusFeat
  with MartialArtsFeat with FeaturesImpl with GrantAbilityFeature {
  self: GeneralFeat =>
  override def allOfFeats: Seq[Feat] = Seq(Feat.withName("Weapon Focus: Slashing"))

  override def anyOfFeats: Seq[Feat] = Seq(
    Feat.withName("Proficiency: Longswords"),
    DeityFeat.FollowerOfTheSovereignHost,
    RacialFeat.HalfElfDilettanteFighter)

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Monk, 1))

  override val grantBonusType: BonusType = BonusType.Feat
  override val grantedAbility: ActiveAbilities = ActiveAbilities.WhirlingSteelStrike
    override protected[this] val triggerOn: TriggerEvent = TriggerEvent.OnCentered
    override protected[this] val triggerOff: TriggerEvent = TriggerEvent.OnOffCentered
    override protected[this] val categories: Seq[effect.EffectCategories.Value] = Seq(effect.EffectCategories.Ability)
    override val abilityId: String = "WhirlingSteelStrike"
    override val description: String = "You treat longswords as if they were monk weapons, remaining centered when you wield them."
}
