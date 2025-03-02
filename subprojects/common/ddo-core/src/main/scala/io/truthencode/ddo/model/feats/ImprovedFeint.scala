/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ImprovedFeint.scala
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
import io.truthencode.ddo.support.requisite.{
  FeatRequisiteImpl,
  RequiresAllOfFeat,
  RequiresAnyOfFeat
}

import java.time.Duration

/**
 * [[https://ddowiki.com/page/Improved_Feint Improved Feint]] Icon Feat Improved Feint.png Improved
 * Feint -- Active - Special Attack A tactical melee attack which Bluffs the enemy, enabling Sneak
 * Attacks.
 *
 * Combat Expertise, MustContainAtLeastOne of : Sneak Attack or Half-Elf Dilettante: Rogue
 *
 * @todo
 *   Implement MustContainAtLeastOneOf(Sneak Attack or Half-Elf Dilettante: Rogue)
 */
protected[feats] trait ImprovedFeint
  extends FeatRequisiteImpl with TriggeredActivationImpl with BonusSelectableToClassFeatImpl
  with ActiveFeat with AtWillEvent with RequiresAnyOfFeat with RequiresAllOfFeat
  with FighterBonusFeat with FeaturesImpl with GrantAbilityFeature {
  self: GeneralFeat =>
  override lazy val grantedAbility: ActiveAbilities = ActiveAbilities.ImprovedFeint
  override val grantBonusType: BonusType = BonusType.Feat
  override protected val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.AtWill)
  override protected val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.OnCoolDown)
  override protected val grantAbilityCategories: Seq[effect.EffectCategories.Value] =
    Seq(effect.EffectCategories.Ability, effect.EffectCategories.SpecialAttack)
  override val abilityId: String = "ImprovedFeint"
  override val description: String =
    "Special Attack A tactical melee attack which Bluffs the enemy, enabling Sneak Attacks."

  override def anyOfFeats: Seq[Feat] = List(ClassFeat.SneakAttack)

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(6))

  override def allOfFeats: Seq[Feat] = List(GeneralFeat.CombatExpertise)
}
