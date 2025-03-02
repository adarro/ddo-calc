/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ExtraInfoTest.scala
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
package io.truthencode.ddo

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.spells.SpellPower.Positive
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class ExtraInfoTest
  extends AnyFunSpec with Matchers with LazyLogging with ScalaCheckPropertyChecks {
  final val Positive = "+"
  final val Negative = "-"
  describe("Extra Info should support ") {
    it("Signed / Unsigned Positive Numbers") {
      forAll { (n: Int) =>
        whenever(n > -1) {
          val ei = ExtraInfo(n)
          ei.symbol shouldEqual Positive
        }
      }
    }
    it("Signed Negative Numbers") {
      forAll { (n: Int) =>
        whenever(n < -1) {
          val ei = ExtraInfo(n)
          ei.symbol shouldEqual Negative
        }
      }
    }
  }

}
