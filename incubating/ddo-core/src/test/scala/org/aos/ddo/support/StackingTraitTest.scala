/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
package io.truthencode.ddo.support

import com.typesafe.scalalogging.LazyLogging
import enumeratum.EnumEntry
import io.truthencode.ddo.activation.PassiveActivation
import io.truthencode.ddo.model.effect.{ActiveEvent, PassiveEvent, TriggerEvent}
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
