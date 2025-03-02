/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: EffectParameterBuilderTest.scala
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
package io.truthencode.ddo.model.effect

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.enhancement.BonusType
import org.scalatest.TryValues.convertTryToSuccessOrFailure
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class EffectParameterBuilderTest extends AnyFunSpec with Matchers with LazyLogging {
  describe("Effect Parameter Builder") {
    it("Should be able to be built with just required values") {
      def builder: EffectParameterList =
        EffectParameterBuilder()
          .toggleOnValue(TriggerEvent.Passive)
          .toggleOffValue(TriggerEvent.Never)
          .addBonusType(BonusType.Alchemical)
          .build
      noException shouldBe thrownBy(builder)
      builder.modifiers.foreach { p => (p.parameter should be).a(Symbol("success")) }
    }

    it("Should function with All optional values") {
      def builder: EffectParameterList = EffectParameterBuilder()
        .toggleOnValue(TriggerEvent.Passive)
        .toggleOffValue(TriggerEvent.Never)
        .addBonusType(BonusType.Feat)
        .addMagnitude()
        .addDifficultyCheck()
        .build
      noException shouldBe thrownBy(builder)
      builder.modifiers.foreach { p => (p.parameter should be).a(Symbol("success")) }
    }

    it("Should ensure no duplicate values") {
      // set the toggleOffValue multiple times
      def builder: EffectParameterList =
        EffectParameterBuilder()
          .toggleOnValue(TriggerEvent.Passive)
          .toggleOffValue(TriggerEvent.OnRest)
          .addBonusType(BonusType.Alchemical)
          .toggleOffValue(TriggerEvent.OnToggle)
          .toggleOffValue(TriggerEvent.Never)
          .build

      val list =
        for m <- builder.modifiers
        yield (m.parameter.success.value.entryName, m.parameter.success.value)

      list.foreach { v =>
        logger.info(v._1)
      }
    }

    it("Should allow optional values") {
      """
        def makePizzaWithOptional: EffectParameterList =
            EffectParameterBuilder()
                .toggleOnValue(TriggerEvent.OnRest)
                .addBonusType(BonusType.Festive)
                .addMagnitude()
                .toggleOffValue(TriggerEvent.OnStance)
                .build
                """.stripMargin should compile
    }
    it("should not allow omitting required values") {
      // Missing required BonusType
      """
        |   def makePizzaWithOptional: EffectParameterList =
        |        EffectParameterBuilder()
        |          .toggleOnValue(TriggerEvent.OnRest)
        |          .addMagnitude()
        |          .toggleOffValue(TriggerEvent.OnStance)
        |          .build""".stripMargin shouldNot compile
    }
  }

}
