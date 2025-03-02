/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Sneak.scala
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
import io.truthencode.ddo.model.misc.DefaultCoolDown
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
 * [[https://ddowiki.com/page/Sneak Sneak]] The character becomes invisible to all enemies that fail
 * a Spot and Listen skill check, opposed by Hide and Move Silently skills.
 * @note
 *   There have been many changes to stealth and it is worth reading, but currently not deeply
 *   invested in this code-base Currently we have it typed as a 'Stance'
 *
 * see [[https://github.com/truthencode/ddo-calc/discussions/9]]
 */
protected[feats] trait Sneak
  extends FeatRequisiteImpl with TriggeredActivationImpl with ActiveFeat with Stance
  with DefaultCoolDown with FreeFeat with FeaturesImpl with GrantAbilityFeature {
  self: GeneralFeat =>
  override lazy val grantedAbility: ActiveAbilities = ActiveAbilities.Sneak
  override protected val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.OnToggle)
  override protected val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.OnToggle)
  override protected val grantAbilityCategories: Seq[effect.EffectCategories.Value] = Seq(
    effect.EffectCategories.Stance)
  override val abilityId: String = "Sneak"
  override val description: String =
    "The character becomes invisible to all enemies that fail a Spot and Listen\n * skill check, opposed by Hide and Move Silently skills."
  override val grantBonusType: BonusType = BonusType.Feat
}
