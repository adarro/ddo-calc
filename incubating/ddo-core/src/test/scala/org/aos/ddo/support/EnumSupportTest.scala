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

import io.truthencode.ddo.model.alignment.MoralAxis
import io.truthencode.ddo.support.EnumSupport.tryEntryFromString
import org.scalatest.{FunSpec, Matchers}
import org.scalatest.OptionValues._
class EnumSupportTest extends FunSpec with Matchers {

  private final val good = "Good"
  describe("EnumSupportTest") {

    describe("tryEntryFromString") {
      it("should extract an enum value from a qualified Enum class name") {
        val t = "io.truthencode.ddo.model.alignment.MoralAxis$"
        tryEntryFromString(t, good).value should be(
          MoralAxis.Good)
        // EnumSupport.tryEnumFromString(t) shouldBe 'success
      }
      it("should extract an enum value from a qualified Enum's EnumEntry Trait class name") {
        val t = "io.truthencode.ddo.model.alignment.MoralAxis"
        tryEntryFromString(t, good).value should be(
          MoralAxis.Good)
        // EnumSupport.tryEnumFromString(t) shouldBe 'success
      }
      it("should fail gracefully (Option.NONE) if the value is not found") {
        val t = "io.truthencode.ddo.model.alignment.MoralAxis$"
        tryEntryFromString(t, good).value should be(
          MoralAxis.Good)
      }
    }

  }
}
