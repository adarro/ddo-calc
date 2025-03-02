/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: TriggerValidationTest.scala
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
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

class TriggerValidationTest
  extends AnyFunSpec with Matchers with LazyLogging with TableDrivenPropertyChecks {
  final val invalidTriggerOn = Seq("Every third Thursday when the moon is full")
  final val invalidTriggerOff = Seq("Prior Relationships")
  final val validTriggerOn = Seq("OnDamage")
  final val validTriggerOff = Seq("OnSpellCast")
  final val someValidTriggerOn = invalidTriggerOn ++ validTriggerOn
  describe("Trigger validation") {

    they("should validate based on names") {
      val trigs = validTriggerOn ++ validTriggerOff
      val trig = validateTriggers(trigs)
      trig.isSuccess shouldBe true
    }

    they("should reject invalid names") {
      val trigs = invalidTriggerOff ++ invalidTriggerOn
      val trig = validateTriggers(trigs)
      trig.isFailure shouldBe true
    }

    they("should gracefully filter out invalid names") {
      val trigs = someValidTriggerOn
      val expectedSize = validTriggerOn.size
      val trig = validateTriggers(trigs)
      trig.isSuccess shouldBe true
      trig.toOption.get should have size expectedSize
    }

  }
}
