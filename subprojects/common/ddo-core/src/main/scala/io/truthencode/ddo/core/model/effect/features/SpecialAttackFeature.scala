/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: SpecialAttackFeature.scala
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
package io.truthencode.ddo.model.effect.features

import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent

/**
 * Convenience trait to set activation etc for special attacks
 */
trait SpecialAttackFeature {
  self: GrantAbilityFeature =>
  override protected lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.SpecialAttack)
  override protected lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.OnCoolDown)
  override protected lazy val grantAbilityCategories: Seq[effect.EffectCategories.Value] =
    Seq(effect.EffectCategories.Ability, effect.EffectCategories.SpecialAttack)
}
