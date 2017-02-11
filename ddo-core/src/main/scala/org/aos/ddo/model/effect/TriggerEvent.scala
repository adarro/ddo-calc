package org.aos.ddo.model.effect

import enumeratum.{Enum => SmartEnum}
import enumeratum.EnumEntry
import org.aos.ddo.support.IndexedEnum

/**
  * Determines when or how an effect occurs
  */
sealed trait TriggerEvent extends EnumEntry

/**
  * Empty Interface that denotes a given effect is "Always On"
  */
trait PassiveEvent extends TriggerEvent

/**
  * Event occurs based on some event
  */
trait ActiveEvent extends TriggerEvent

object TriggerEvent extends IndexedEnum[TriggerEvent] {
  override val values: Seq[TriggerEvent] = findValues
  case object Passive extends PassiveEvent
  /**
    * Occurs on every attack
    */
  case object OnAttack extends ActiveEvent
  /**
    * Occurs on a specific range of attack rolls
    */
  case object OnAttackRoll extends ActiveEvent
  /**
    * Occurs when you are damaged (hit)
    */
  case object OnDamage extends ActiveEvent
  /**
    * Occurs when you are hit by a spell
    */
  case object OnSpellHit extends ActiveEvent
  /**
    * Occurs when you cast a spell
    */
  case object OnSpellCast extends ActiveEvent
  /**
    * Occurs when you are killed
    */
  case object OnDeath extends ActiveEvent
  /**
    * Occurs when you are incapacited
    *
    * Hit points fall below 0
    */
  case object OnIncapacitated extends ActiveEvent
  /**
    * Occurs upon waking from rest / shrine
    */
  case object OnRest extends ActiveEvent

  /**
    * Can be toggled on / off as desired
    */
  case object OnToggle extends ActiveEvent

  /**
    * Activated At Will / Button press such as Kick / Sunder and may have a cool-down
    */
  case object AtWill extends ActiveEvent
}
