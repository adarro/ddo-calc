package org.aos.ddo.model.effect

trait Trigger {
  val triggerEvent: TriggerEvent
}
/**
  * Effect is passive and pervasive
  *
  * The effect is generally active provided you have the
  * Feat / Enhancement or wearing the item granting the effect.
  */
trait Passive extends Trigger {
  override val triggerEvent: TriggerEvent = TriggerEvent.Passive
}
