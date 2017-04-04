package org.aos.ddo.activation

import com.typesafe.scalalogging.LazyLogging
import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.model.effect.TriggerEvent.Passive
import org.aos.ddo.model.effect.{ActiveEvent, PassiveEvent, TriggerEvent}

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
  case class Active(triggers: ActiveEvent*) extends {
    override val activeTriggers: Set[TriggerEvent] = {
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
    TriggerEvent.values.filter(p => filterActive(p)) map { x =>
      Active(x.asInstanceOf[ActiveEvent])
    }

  }
  @volatile
  override lazy val values = findValues ++ dynamics
}

trait TriggeredActivation
    extends ActivationType
    with Trigger
    with ActiveEvent
    with LazyLogging { self: EnumEntry =>
  abstract override lazy val activations
    : Set[TriggerEvent] = super.activations ++ activeTriggers
}
