package org.aos.ddo.effect

import enumeratum.{ Enum => SmartEnum }
import enumeratum.EnumEntry

/**
  * Determines when or how an effect occurs
  */
sealed trait TriggerEvent extends EnumEntry
object TriggerEvent extends SmartEnum[TriggerEvent] {
  override val values: IndexedSeq[org.aos.ddo.effect.TriggerEvent] = findValues
  case object Passive extends TriggerEvent
  /**
    * Occurs on every attack
    */
  case object OnAttack extends TriggerEvent
  /**
    * Occurs on a specific range of attack rolls
    */
  case object OnAttackRoll extends TriggerEvent
  /**
    * Occurs when you are damaged (hit)
    */
  case object OnDamage extends TriggerEvent
  /**
    * Occurs when you are hit by a spell
    */
  case object OnSpellHit extends TriggerEvent
  /**
    * Occurs when you cast a spell
    */
  case object OnSpellCast extends TriggerEvent
  /**
    * Occurs when you are killed
    */
  case object OnDeath extends TriggerEvent
  /**
    * Occurs when you are incapacited
    *
    * Hit points fall below 0
    */
  case object OnIncapacitated extends TriggerEvent
  /**
    * Occurs upon waking from rest / shrine
    */
  case object OnRest extends TriggerEvent
}
