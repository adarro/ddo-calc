/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: AttributeTest.scala
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
package io.truthencode.ddo.model.attribute

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableFor2
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class AttributeTest
  extends AnyFunSpec with ScalaCheckPropertyChecks with Matchers with LazyLogging {
  val nameMap: TableFor2[String, String] =
    Table(
      ("abbr", "name"),
      ("STR", "Strength"),
      ("WIS", "Wisdom"),
      ("INT", "Intelligence"),
      ("CON", "Constitution"),
      ("DEX", "Dexterity"),
      ("CHA", "Charisma")
    )

  describe("An Attribute ") {
    it("Should support both abbreviations and full words") {
      forAll(nameMap) { (abbr: String, name: String) =>
        val atr = Attribute.withName(name)
        atr.abbr shouldEqual abbr
        atr.toFullWord shouldEqual name
      }
    }
  }
}
