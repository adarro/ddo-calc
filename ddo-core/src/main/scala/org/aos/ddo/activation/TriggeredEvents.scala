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