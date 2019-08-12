package org.aos.ddo.support

import com.typesafe.scalalogging.LazyLogging
import enumeratum.EnumEntry
import org.aos.ddo.activation.PassiveActivation
import org.aos.ddo.model.effect.{ActiveEvent, PassiveEvent, TriggerEvent}
import org.scalatest.{FunSpec, Matchers}

/**
  * Created by adarr on 2/6/2017.
  */
class StackingTraitTest extends FunSpec with Matchers with LazyLogging {

  object Fixture {

    trait ActivationType extends EnumEntry {
      self: TriggerEvent =>
      def activations: Set[TriggerEvent] = Set.empty //  HashSet[TriggerEvent]()
    }

  }

  describe("Trigger Events") {
    it ("has supports passive Activation") {
      val pActivation = new PassiveActivation {}
      logger.info(s"pActivation entryName : ${pActivation.entryName}")
    //  pActivation.entryName
    }
    it("has dynamic enum values") {
      noException should be thrownBy TriggerEvent.values
      TriggerEvent.values should not be empty
    }
    it("Supports basic stacking") {
      val passive = new Fixture.ActivationType with PassiveEvent {}
      passive.activations should be(empty)
    }
    it("Supports dynamic stacking") {
      val active = new Fixture.ActivationType with ActiveEvent {}
      active.activations should be(empty)
    }
  }
}
