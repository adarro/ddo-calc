package org.aos.ddo.activation

import org.aos.ddo.model.effect.TriggerEvent

import scala.collection.immutable.HashSet

/**
  * Created by adarr on 2/6/2017.
  */
trait Trigger {
    def activeTriggers: Set[TriggerEvent] = new HashSet[TriggerEvent]()
}
