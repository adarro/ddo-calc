/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Resilience.scala
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

import io.truthencode.ddo.activation.TriggeredActivationImpl
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.abilities.ActiveAbilities
import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{FeaturesImpl, GrantAbilityFeature}
import io.truthencode.ddo.support.requisite.{
  AttributeRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfAttribute,
  RequiresBaB
}

import java.time.Duration

/**
 * Icon Feat Resilience.png [[https://ddowiki.com/page/Resilience Resilience]] Active - Defensive
 * Combat Stance You gain a +4 to all saving throws. Spells have three times their normal cooldown
 * when this mode is active.
 *
 * Constitution 13 Base Attack Bonus +1
 * @todo
 *   Adds 3x spell cooldown timers
 */
protected[feats] trait Resilience
  extends FeatRequisiteImpl with TriggeredActivationImpl with BonusSelectableToClassFeatImpl
  with ActiveFeat with DefensiveCombatStance with AttributeRequisiteImpl with RequiresAllOfAttribute
  with RequiresBaB with FighterBonusFeat with MartialArtsFeat with FeaturesImpl
  with GrantAbilityFeature {
  self: GeneralFeat =>
  override lazy val grantedAbility: ActiveAbilities = ActiveAbilities.Resilience
  override val grantBonusType: BonusType = BonusType.Feat
// TODO: Add 3x spell cool down, +4 saving throw
  override protected val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.OnStance)
  override protected val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.OnToggle)
  override protected val grantAbilityCategories: Seq[effect.EffectCategories.Value] = Seq(
    effect.EffectCategories.Stance)
  override val abilityId: String = "Resilience"
  override val description: String = "Defensive Combat Stance You gain a +4 to all saving throws."

  override def allOfAttributes: Seq[(Attribute, Int)] =
    List((Attribute.Constitution, 13))

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(6))

  override def requiresBaB: Int = 1
}
