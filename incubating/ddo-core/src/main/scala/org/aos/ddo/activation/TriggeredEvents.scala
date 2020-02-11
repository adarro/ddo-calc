/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
package org.aos.ddo.activation

import org.aos.ddo.model.effect.TriggerEvent
import org.aos.ddo.model.effect.TriggerEvent._


/**
  * Occurs on every attack
  */
trait AttackEvent extends TriggeredActivation {
  abstract override lazy val activeTriggers: Set[TriggerEvent] = super.activeTriggers + OnAttack
}

/**
  * Occurs on a specific range of attack rolls
  */
trait AttackRollEvent extends TriggeredActivation {
  abstract override lazy val activeTriggers: Set[TriggerEvent] = super.activeTriggers + OnAttackRoll
}

/**
  * Occurs when you are damaged (hit)
  */
trait OnDamageEvent extends TriggeredActivation {
  abstract override lazy val activeTriggers: Set[TriggerEvent] = super.activeTriggers + OnDamage
}

/**
  * Occurs when you are hit by a spell
  */
trait OnSpellHitEvent extends TriggeredActivation {
  abstract override lazy val activeTriggers: Set[TriggerEvent] = super.activeTriggers + OnSpellHit
}

/**
  * Occurs when you cast a spell
  */
trait OnSpellCastEvent extends TriggeredActivation {
  abstract override lazy val activeTriggers: Set[TriggerEvent] = super.activeTriggers + OnSpellCast
}

/**
  * Occurs when you are killed
  */
trait OnDeathEvent extends TriggeredActivation {
  abstract override lazy val activeTriggers: Set[TriggerEvent] = super.activeTriggers + OnDeath
}

/**
  * Occurs when you are incapacited
  *
  * Hit points fall below 0
  */
trait OnIncapacitatedEvent extends TriggeredActivation {
  abstract override lazy val activeTriggers: Set[TriggerEvent] = super.activeTriggers + OnIncapacitated
}

/**
  * Occurs upon waking from rest / shrine
  */
trait OnRestEvent extends TriggeredActivation {
  abstract override lazy val activeTriggers: Set[TriggerEvent] = super.activeTriggers + OnRest
}

/**
  * Player can toggle this effect on / off
  */
trait OnToggleEvent extends TriggeredActivation {
  abstract override lazy val activeTriggers: Set[TriggerEvent] = super.activeTriggers + OnToggle
}

trait AtWillEvent extends TriggeredActivation {
  abstract override lazy val activeTriggers: Set[TriggerEvent] = super.activeTriggers + AtWill
}