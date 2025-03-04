/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: TriggeredEvents.scala
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
package io.truthencode.ddo.activation

import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.TriggerEvent._
import io.truthencode.ddo.model.feats.Toggle

/**
 * Occurs on every attack
 */
trait AttackEvent extends TriggeredActivation {
  abstract override def activatableTriggers: Seq[TriggerEvent] =
    super.activatableTriggers :+ OnAttack
}

trait TriggeredEventImpl extends TriggeredActivation {
  override def activatableTriggers: Seq[TriggerEvent] = Nil
}

/**
 * Occurs on a specific range of attack rolls
 */
trait AttackRollEvent extends TriggeredActivation {
  abstract override def activatableTriggers: Seq[TriggerEvent] =
    super.activatableTriggers :+ OnAttackRoll
}

/**
 * Occurs when you are damaged (hit)
 */
trait OnDamageEvent extends TriggeredActivation {
  abstract override def activatableTriggers: Seq[TriggerEvent] =
    super.activatableTriggers :+ OnDamage
}

/**
 * Occurs when you are hit by a spell
 */
trait OnSpellHitEvent extends TriggeredActivation {
  abstract override def activatableTriggers: Seq[TriggerEvent] =
    super.activatableTriggers :+ OnSpellHit
}

trait HealthyEvent extends TriggeredActivation {
  abstract override def activatableTriggers: Seq[TriggerEvent] =
    super.activatableTriggers :+ OnHealthy
}

/**
 * Occurs when you cast a spell
 */
trait OnSpellCastEvent extends TriggeredActivation {
  abstract override def activatableTriggers: Seq[TriggerEvent] =
    super.activatableTriggers :+ OnSpellCast
}

/**
 * Occurs when you cast a spell like ability
 */
trait OnSpellLikeAbilityEvent extends TriggeredActivation {
  abstract override def activatableTriggers: Seq[TriggerEvent] =
    super.activatableTriggers :+ OnSpellLikeAbility
}

trait OnSongPlayedEvent extends TriggeredActivation {
  abstract override def activatableTriggers: Seq[TriggerEvent] =
    super.activatableTriggers :+ OnSong
}

/**
 * Occurs when you are killed
 */
trait OnDeathEvent extends TriggeredActivation {
  abstract override def activatableTriggers: Seq[TriggerEvent] =
    super.activatableTriggers :+ OnDeath
}

/**
 * Occurs when you are incapacitated
 *
 * Hit points fall below 0
 */
trait OnIncapacitatedEvent extends TriggeredActivation {
  abstract override def activatableTriggers: Seq[TriggerEvent] =
    super.activatableTriggers :+ OnIncapacitated
}

/**
 * Occurs upon waking from rest / shrine
 */
trait OnRestEvent extends TriggeredActivation {
  abstract override def activatableTriggers: Seq[TriggerEvent] = super.activatableTriggers :+ OnRest
}

/**
 * Player can toggle this effect on / off
 */
trait OnToggleEvent extends TriggeredActivation with Toggle {
  abstract override def activatableTriggers: Seq[TriggerEvent] =
    super.activatableTriggers :+ OnToggle
}

trait AtWillEvent extends TriggeredActivation {
  abstract override def activatableTriggers: Seq[TriggerEvent] = super.activatableTriggers :+ AtWill
}

trait OnTavern extends TriggeredActivation {
  abstract override def activatableTriggers: Seq[TriggerEvent] =
    super.activatableTriggers :+ InTavern
}

trait OnMeleeSpecialAttack extends TriggeredActivation {
  abstract override def activatableTriggers: Seq[TriggerEvent] =
    super.activatableTriggers :+ SpecialAttack
}

trait OnSummon extends TriggeredActivation {
  abstract override def activatableTriggers: Seq[TriggerEvent] =
    super.activatableTriggers :+ Summon
}

trait OnShapeShift extends TriggeredActivation {
  abstract override def activatableTriggers: Seq[TriggerEvent] =
    super.activatableTriggers :+ ShapeChange
}
