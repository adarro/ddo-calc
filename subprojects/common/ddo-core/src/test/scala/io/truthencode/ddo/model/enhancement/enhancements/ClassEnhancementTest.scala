/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ClassEnhancementTest.scala
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
package io.truthencode.ddo.model.enhancement.enhancements

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.support.StringUtils._
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class ClassEnhancementTest extends AnyFunSpec with Matchers with LazyLogging {
  describe("A Class Enhancement") {
    it("should have a description") {
      val d = ClassEnhancement.Determination
      d.description shouldNot be(empty)
      d.rawDescription shouldEqual ("+1 Will Save")
    }
    it("should have Tier information") {
      ClassEnhancement.Determination.tier shouldEqual ("Core")
    }
    it("should read names with spaces") {
      val name = "Alchemical Shield".toPascalCase
      val e = ClassEnhancement.AlchemicalShield

      val eo = ClassEnhancement.withNameOption(name)
      logger.info(s"attempting to find => ${e.entryName} using withName $name")

      eo shouldNot be(empty)

    }
    it("should support Roman Numerals") {
      val e = ClassEnhancement.SpellCriticalElementalAndPoisonII
      val eDt = e.displayText
      val eId = e.entryName
      val dTest = "Spell Critical: Elemental and Poison II"
      eDt shouldEqual dTest
    }

    it("should include poison and elemental") {
      val e = ClassEnhancement.HiddenBladesII
      val eDt = e.displayText
      val eId = e.entryName
      val dTest = "Hidden Blades II"
      eDt shouldEqual dTest
    }
    it("should now support ampersands") {
      val e = ClassEnhancement.SpellCriticalChancePositiveAndNegativeI
      val eDt = e.displayText
      val eId = e.entryName

      val dText = "Spell Critical Chance: Positive & Negative I"
      val id = "SpellCriticalChancePositiveAndNegativeI"
      val searchWithName = dText.symbolsToWords.toPascalCase.filterAlphaNumeric
      e.entryName shouldEqual id
      e.displayText shouldEqual dText
      searchWithName shouldEqual id
      logger.info("checking on Spell Critical")
    }
    it("should properly case noise words") {
      val e = ClassEnhancement.SpellCriticalElementalAndPoisonI
      val eDt = e.displayText
      val eId = e.entryName
      val st = 0
    }
  }
}
