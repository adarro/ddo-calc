/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
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

import com.typesafe.scalalogging.LazyLogging
import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.model.effect.TriggerEvent.Passive
import io.truthencode.ddo.model.effect.{ActiveEvent, PassiveEvent, TriggerEvent}

import scala.collection.immutable.HashSet
import scala.reflect.ClassTag

/**
 * Determines whether the given effect is Active or Passively triggered
 */
sealed trait ActivationType extends EnumEntry { self: TriggerEvent =>
  def activations: Set[TriggerEvent] = init

  private[this] val init = new HashSet[TriggerEvent]()
}

/**
 * Denotes the effect is passive and always on.
 */
trait PassiveActivation extends ActivationType with PassiveEvent {
  abstract override def activations: Set[TriggerEvent] =
    super.activations + Passive
}

object ActivationType extends Enum[ActivationType] with LazyLogging {

  /**
   * This effect is always on
   */
  case object Passive extends PassiveActivation

  /**
   * This effect is triggered by some means such as a toggle, threshold or status.
   */
  case class Active(triggers: ActiveEvent*)
    extends {
      override val activatableTriggers: Set[TriggerEvent] = {
        triggers.toSet
      }
    } with TriggeredActivation with LazyLogging {
    override def entryName: String = {
      this.triggers.headOption match {
        case Some(x) => x.entryName
        case _ => "Active"
      }
    }
  }

  private def filterActive[T <: TriggerEvent: ClassTag](c: T): Boolean = {
    c match {
      case _: ActiveEvent => true
      case _ => false
    }
  }

  lazy val dynamics: Seq[Active] = {
    TriggerEvent.values.filter(p => filterActive(p)).map { x =>
      Active(x.asInstanceOf[ActiveEvent])
    }

  }
  @volatile
  override lazy val values = findValues ++ dynamics
}

trait TriggeredActivation extends ActivationType with Trigger with ActiveEvent with LazyLogging { self: EnumEntry =>
  abstract override def activations: Set[TriggerEvent] = super.activations ++ activatableTriggers
}
