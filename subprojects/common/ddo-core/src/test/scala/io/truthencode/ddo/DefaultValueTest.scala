/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
import org.scalatest.{FunSpec, Matchers}


class DefaultValueTest extends FunSpec with Matchers with LazyLogging {
  final val greeting = "Hello World"
  val noDefault: DefaultValue[String] = new DefaultValue[String] with NoDefault[String] {}
  val greetDefault = new DefaultValue[String] {
    override lazy val default = Some(greeting)
  }
  val otherGreet = new DefaultValue[String] {
    override lazy val default = Some(greeting)
  }
  describe("A Default Value ") {
    it("should be able to check if it has one") {
      noDefault.hasDefault should be(false)
    }

    it("can be checked against None") {
      noDefault.default should be(empty)
    }

    it("May have a value") {
      greetDefault.hasDefault should be(true)
    }

    it("can be verified to have a value") {
      val thisGreeting: Option[String] = Some(greeting)
      greetDefault.default should be(thisGreeting)
    }

    it("can see if it is the default value (equals)") {
      val thisGreeting = Some(greeting)
      greetDefault.isDefault(otherGreet) should be(true)
    }
    describe("isDefault with no default") {

      it("should return false if there is no default value even when compared to itself ") {
        noDefault.isDefault(noDefault) should be(false)
      }

      it("should return false if there is no default value even when compared to other values ") {
        assume(!noDefault.hasDefault)
        noDefault.default should be(empty)
        noDefault.isDefault(greetDefault) should be(false)
      }
    }
  }
}
