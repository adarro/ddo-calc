/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
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
package io.truthencode.ddo.model.spells

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.spells.SpellElement.{WithName, WithSpellInfo}
import org.scalatest.{FunSpec, Matchers}

class SpellElementTest extends FunSpec with Matchers with LazyLogging {

  describe("Utility Functions") {
    it("Should extract an element by type correctly") {
      val eName = UseSpellName(name = "Example Spell")

      val elements: Seq[SpellElement] = Seq(eName)
      val n: Iterable[WithName] =
        SpellElement.extract[WithName](elements.toList)
      n should contain(eName)
      //      val se: Seq[SpellElement] => SpellElement.Util[WithName] = SpellElement.Util[WithName]
      //      val se2: Seq[Nothing] = se.apply(elements).getStuff(elements)
      //      val results: Seq[SpellElement] => Seq[WithName] = elements.getStuff[WithName]
      //
      //      results shouldBe defined
      //      val n =results.apply(elements).headOption
      //       n shouldBe defined
      //
      //      n shouldBe defined
      //      n shouldEqual Some(eName.name)
      //  results.ma
    }

    it(
      "Should extract an element by type correctly directly from the collection"
    ) {
      val eName = UseSpellName(name = "Example Spell")

      val elements: Seq[SpellElement] = Seq(eName)
      val n: Iterable[WithName] = elements.extract[WithName]
      n should contain(eName)

    }

    it("Should gracefully fail to find an missing element by type") {
      val eName = UseSpellName(name = "Example Spell")

      val elements: Seq[SpellElement] = Seq(eName)
      val n = SpellElement.extract[WithSpellInfo](elements.toList)
      n shouldNot contain(eName)
    }
    it(
      "Should gracefully fail to find an missing element by type from the collection"
    ) {
      val eName = UseSpellName(name = "Example Spell")

      val elements: Seq[SpellElement] = Seq(eName)

      val n = elements.extract[WithSpellInfo]
      n shouldNot contain(eName)
    }
  }
}
