/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ScalingValidationTest.scala
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
package io.truthencode.ddo.support.validation

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.api.model.effect.{ScalingEffect, ScalingInfo}
import io.truthencode.ddo.support.validation.ScalingValidation.{
  validateScalingPower,
  validateScalingType
}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import zio.prelude.ZValidation

class ScalingValidationTest
  extends AnyFunSpec with Matchers with LazyLogging with TableDrivenPropertyChecks {
  describe("Scaling Info") {
    val validPowers = Table(
      ("name", "value"),
      ("SpellPower", 50),
      ("MeleePower", 23),
      ("RangedPower", 322)
    )

    val invalidPowers = Table(
      ("name", "power"),
      ("OverWater Breathing", 13),
      ("Parcheesy", 300)
    )
    it("should support valid powers") {
      forAll(validPowers) { (name: String, value: Int) =>
        val v = validateScalingType(name)
        v.isSuccess shouldBe true
      }
    }

    it("should reject invalid powers") {
      forAll(invalidPowers) { (name: String, value: Int) =>
        val v = validateScalingType(name)
        v.isFailure shouldBe true
      }
    }

    it("should support an  Optional List of values") {
      val lst: Option[Seq[ScalingInfo]] = None
      given filterStrategy: invalidationOptions = invalidationOptions.FilterValid
      validateScalingPower(lst).isSuccess shouldBe true
    }

    it("should fail on completely invalid List of values") {
      val lst: Option[Seq[ScalingInfo]] =
        Some(invalidPowers.toList.map((p, v) => ScalingEffect(value = v, source = p)))

      given filterStrategy: invalidationOptions = invalidationOptions.FilterValid

      validateScalingPower(lst).isFailure shouldBe true
    }

    it("should support a specified valid list of values") {
      given filterStrategy: invalidationOptions = invalidationOptions.FilterValid
      val oList = Some(validPowers.toList.map((p, v) => ScalingEffect(value = v, source = p)))
      val result = validateScalingPower(oList)
      result.isSuccess shouldBe true
    }

    it("should support a specified with a completely valid list of values with RejectAll") {
      given filterStrategy: invalidationOptions = invalidationOptions.RejectAll

      val oList = Some(validPowers.toList.map((p, v) => ScalingEffect(value = v, source = p)))
      val result = validateScalingPower(oList)
      result.isSuccess shouldBe true
    }

    it("should support some valid values when filterStrategy allows it") {
      given filterStrategy: invalidationOptions = invalidationOptions.FilterValid
      val mixedValid = validPowers ++ invalidPowers
      val oList = Some(mixedValid.toList.map((p, v) => ScalingEffect(value = v, source = p)))
      val result = validateScalingPower(oList)
      result.isSuccess shouldBe true
      result.toOption.flatten.get should have size 3
    }

    it("should support reject all if any invalid values are found when filterStrategy specifies it") {
      given filterStrategy: invalidationOptions = invalidationOptions.RejectAll

      val mixedValid = validPowers ++ invalidPowers
      val oList = Some(mixedValid.toList.map((p, v) => ScalingEffect(value = v, source = p)))
      val result = validateScalingPower(oList)
      result.isFailure shouldBe true
    }
  }
}
