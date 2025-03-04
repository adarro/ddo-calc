/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Hamstring.scala
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

import io.truthencode.ddo.activation.{AtWillEvent, TriggeredActivationImpl}
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.abilities.ActiveAbilities
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{FeaturesImpl, GrantAbilityFeature}
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

import java.time.Duration

/**
 * Icon Feat Hamstring.png [[https://ddowiki.com/page/Hamstring Hamstring]] Hamstring Active -
 * Special Attack This melee special attack, when successful, reduces the target's movement rate by
 * half for 12 seconds. Some creatures may be immune to the hamstring effect.
 *
 * Sneak Attack
 */
protected[feats] trait Hamstring
  extends FeatRequisiteImpl with TriggeredActivationImpl with BonusSelectableToClassFeatImpl
  with ActiveFeat with AtWillEvent with RequiresAllOfFeat with FighterBonusFeat with FeaturesImpl
  with GrantAbilityFeature {
  self: GeneralFeat =>
  override lazy val grantedAbility: ActiveAbilities = ActiveAbilities.Hamstring
  override protected lazy val grantAbilityCategories: Seq[effect.EffectCategories.Value] =
    Seq(effect.EffectCategories.Ability, effect.EffectCategories.SpecialAttack)
  override val grantBonusType: BonusType = BonusType.Feat
  override val allOfFeats: Seq[Feat] = List(ClassFeat.SneakAttack)
  override protected val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.SpecialAttack)
  override protected val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.OnCoolDown)
  override val abilityId: String = "Hamstring"
  override val description: String =
    "Special Attack This melee special attack, when successful, reduces the target's movement rate by half for 12 seconds."

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(15))
}
