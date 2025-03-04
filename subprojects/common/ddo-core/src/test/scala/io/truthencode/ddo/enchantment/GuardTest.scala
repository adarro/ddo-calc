/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: GuardTest.scala
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
package io.truthencode.ddo.enchantment

import com.typesafe.scalalogging.LazyLogging

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
// import org.junit.runner.RunWith

/**
 * Validation of Guard Enchantments is outdated and needs to be updated. Likely an entire new test
 * suite is needed in addition to new logic.
 */
// @RunWith(classOf[JUnitRunner])
class GuardTest extends AnyFunSpec with Matchers with LazyLogging {
  val rnIV = "IV"
  describe("A Guard Enchantment") {
    it("Can support a Roman Numeral 1 - 10") {
      val rn =
        List("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X")

      for x <- rn do {
        GuardModifier.allowedRoman(Option(x)) should not be empty
      }
    }
    it("Can support a Roman Numeral as a suffix") {
      noException should be thrownBy Some(GuardModifier(suffix = Some(rnIV)))
      //   noException should be thrownBy Guard(guard = Guards.AcidGuard, rn4)
    }

    ignore("Will reject an invalid suffix") {
      a[NoSuchElementException] should be thrownBy {
        Some(GuardModifier(suffix = Some("whateva")))
      }
    }

    it("May have a single prefix") {
      noException should be thrownBy Some(GuardModifier(prefix = Some(Modifier.Minor.entryName)))
    }

    ignore("Must reject a disallowed  prefix") {
      an[AssertionError] should be thrownBy {
        Some(GuardModifier(prefix = Some("x")))
        Some(GuardModifier(prefix = Some("m")))
      }
    }

    it("Supports a subset of prefixes") {
      val supported =
        List(Modifier.Minor, Modifier.Lesser, Modifier.Greater).map { x =>
          Some(x.entryName)
        }
      supported.foreach { x =>
        GuardModifier.filterModifiers(x) shouldBe defined
      }
    }

    it("Must Reject Other prefixes") {
      val supported = List(Modifier.Minor, Modifier.Lesser, Modifier.Greater)
      val unSupported = Modifier.values.diff(supported).map { x =>
        Some(x.entryName)
      }
      unSupported.foreach { x =>
        GuardModifier.filterModifiers(x) should not be defined
      }
    }

    ignore("Must reject a invalid  prefix") {
      assume(io.truthencode.ddo.support.AssertionStatus.isEnabled)
      an[AssertionError] should be thrownBy {
        Some(GuardModifier(prefix = Some("Super Uber Rock me Epic")))
      }
    }

    ignore("Must not have a secondary prefix") {
      assume(io.truthencode.ddo.support.AssertionStatus.isEnabled)
      an[AssertionError] should be thrownBy {
        GuardModifier(sPrefix = Some("Uber"))
      }
    }

    ignore("Must not have both a prefix AND a suffix") {
      assume(io.truthencode.ddo.support.AssertionStatus.isEnabled)
      a[AssertionError] should be thrownBy {
        Some(GuardModifier(prefix = Some(Modifier.Minor.entryName), suffix = Some(rnIV)))
      }
    }

    it("Supports Named Guards")(pending)
  }

}
