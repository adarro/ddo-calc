/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Trip.scala
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
import io.truthencode.ddo.model.attribute.{DexterityLinked, LinkedAttributeImpl, StrengthLinked}
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{FeaturesImpl, GrantAbilityFeature}
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

import java.time.Duration

/**
 * This feat has a chance to trip the target rendering it prone for a short time. Strength or
 * Dexterity save (whichever is higher) is used to oppose a DC of 10 + Strength modifier + related
 * Enhancements + Vertigo. Some creatures may be immune to this effect. Creatures larger or stronger
 * than you are less likely to trip. A successful Balance check negates this effect (DC of 10 +
 * Strength modifier + related Enhancements + Vertigo).
 */
protected[feats] trait Trip
  extends FeatRequisiteImpl with TriggeredActivationImpl with ActiveFeat
  //   with DifficultyCheck
  with LinkedAttributeImpl with DexterityLinked with StrengthLinked with Tactical with FreeFeat
  with FeaturesImpl with GrantAbilityFeature {
  self: GeneralFeat =>

  override lazy val grantedAbility: ActiveAbilities = ActiveAbilities.Trip
  override protected val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.AtWill)
  override protected val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.OnCoolDown)
  override protected val grantAbilityCategories: Seq[effect.EffectCategories.Value] = Seq(
    effect.EffectCategories.Ability)
  override val abilityId: String = "Trip"
  override val description: String =
    "This feat has a chance to trip the target rendering it prone for a short time."
  // DC of 10 + Strength modifier + related Enhancements + Vertigo.
  override val grantBonusType: BonusType = BonusType.Feat

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(15))
}
