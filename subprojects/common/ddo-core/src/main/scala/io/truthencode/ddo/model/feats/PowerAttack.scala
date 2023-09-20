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
import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{FeaturesImpl, GrantAbilityFeature}
import io.truthencode.ddo.support.requisite.{
  AttributeRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfAttribute
}

import java.time.Duration

/**
 * [[http://ddowiki.com/page/Power_Attack Power Attack]] This feat exchanges part of your attack
 * bonus for extra melee damage. It reduces your hit bonus by 5, or your Base Attack Bonus,
 * whichever is lower. Then your successful attacks will have their damage increased by the same
 * amount. Two-handed weapons get twice that damage bonus. (Unarmed strikes count as one-handed.)
 * Typically, this means one-handed weapons get +5 and two-handed get +10 to damage.
 *
 * This feat is a stance. It may be toggled on and left active indefinitely. When deactivated, there
 * is a 10 second cooldown before it can be used again.
 *
 * @todo
 *   add Cooldown with Toggle likely inconsequential but only applies when toggling off then on
 *   again
 * @todo
 *   add Offensive Combat Stance
 */
protected[feats] trait PowerAttack
  extends FeatRequisiteImpl with ActiveFeat with Stance with AttributeRequisiteImpl
  with RequiresAllOfAttribute with MartialArtsFeat with FighterBonusFeat with FeaturesImpl
  with GrantAbilityFeature {
  self: GeneralFeat =>
  override lazy val grantedAbility: ActiveAbilities = ActiveAbilities.PowerAttack
  override val allOfAttributes: Seq[(Attribute, Int)] = List(
    (Attribute.Strength, 13)
  )
// TODO: Add to hit penalty, Damage Bonus for PowerAttack
  override protected[this] val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.OnStance)
  override protected[this] val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.OnToggle)
  override protected[this] val grantAbilityCategories: Seq[effect.EffectCategories.Value] =
    Seq(
      effect.EffectCategories.Ability,
      effect.EffectCategories.Stance,
      effect.EffectCategories.MeleeCombat)
  override val abilityId: String = "PowerAttack"
  override val description: String =
    "This feat exchanges part of your attack bonus for extra melee damage. It reduces your hit bonus by 5, or your Base Attack Bonus, whichever is lower."
  override val grantBonusType: BonusType = BonusType.Feat

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(10))
}
