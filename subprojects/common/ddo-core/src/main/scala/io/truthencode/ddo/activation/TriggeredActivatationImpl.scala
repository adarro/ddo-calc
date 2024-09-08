package io.truthencode.ddo.activation

import io.truthencode.ddo.model.effect.TriggerEvent

/**
 * Convienience class for creating a triggered activation.
 */
trait TriggeredActivationImpl extends TriggerImpl with ActivationTypeImpl {
  self : TriggerEvent =>
}
