/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Sunder.scala
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
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{FeaturesImpl, GrantAbilityFeature}
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

import java.time.Duration

/**
 * [[https://ddowiki.com/page/Sunder Sunder]] This melee special attack, when successful, results in
 * a -4 AC penalty to the target for 12 seconds if it fails a Fortitude save (DC 10 + Str mod). Some
 * creatures may be immune to the sunder effect
 */
protected[feats] trait Sunder
  extends FeatRequisiteImpl with TriggeredActivationImpl with ActiveFeat with Tactical with FreeFeat
  with FeaturesImpl with GrantAbilityFeature {
  self: GeneralFeat =>
  override lazy val grantedAbility: ActiveAbilities = ActiveAbilities.Sunder
  override val grantBonusType: BonusType = BonusType.Feat
  override protected val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.AtWill)
  override protected val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.OnCoolDown)
  override protected val grantAbilityCategories: Seq[effect.EffectCategories.Value] = Seq(
    effect.EffectCategories.Ability)
  override val abilityId: String = "Sunder"
  override val description: String =
    "This melee special attack, when successful, results in a -4 AC penalty to the target for 12 seconds"

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(10))
}
