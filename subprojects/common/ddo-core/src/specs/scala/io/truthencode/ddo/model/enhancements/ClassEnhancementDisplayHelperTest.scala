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
package io.truthencode.ddo.model.enhancements

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.enhancement.Tier
import io.truthencode.ddo.model.enhancement.enhancements.ClassEnhancement
import io.truthencode.ddo.support.requisite.{ActionPointRequisite, PointInTreeRequisite}
import io.truthencode.ddo.support.tree.ClassTrees
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}
import io.truthencode.ddo.support.StringUtils._

@RunWith(classOf[JUnitRunner])
class ClassEnhancementDisplayHelperTest extends FunSpec with Matchers with LazyLogging {
  describe("Mapped Values") {
    it("should just work") {
      type ENH = ClassEnhancement with Tier with ActionPointRequisite with PointInTreeRequisite
      val values: Seq[ENH] = ClassEnhancement.values collect { case x: ENH => x }
      val baseSize = ClassEnhancement.values.size
      val tSite = values.size
      val baseNames = ClassEnhancement.values.map(_.entryName)
      val tNames = values.map(_.entryName)
      baseNames.intersect(tNames).size shouldEqual (baseSize)
        val enhancement = "Curative Admixture: Cure Serious Wounds"
        val csw = ClassEnhancement.CurativeAdmixtureCureSeriousWounds
        val cswName = csw.displayText
        val cswId = cswName.toPascalCase.filterAlphaNumeric

        cswName shouldEqual enhancement
      cswId shouldEqual(csw.entryName)

      implicit val identifier: String =  cswId
        val vs =values.filter(_.entryName.contains("Cure"))
      noException shouldBe thrownBy {
        val rslt =  values.find(p => p.entryName.equals(identifier))

      }
//      noException shouldBe thrownBy {
//        val r = values
//          .map { e =>
//            e.entryName -> e.displayText
//          }
//          .map { t =>
//            t._2 -> CEnhancement(t._2)(t._1)
//          }
//          .toMap
//      }
    }
    they("Should support ampersands") {
      val e = ClassEnhancement.SpellCriticalChancePositiveAndNegativeI
      val id = e.entryName
      val dt = e.displayText
      val expectedDisplay = "Spell Critical Chance: Positive & Negative I"
      val expectedId = "SpellCriticalChancePositiveAndNegativeI"
      dt.shouldEqual(expectedDisplay)
      id.shouldEqual(expectedId)
    }
    they("should have values") {
      val t = new ClassEnhancementDisplayHelper {
        override val tree: ClassTrees = ClassTrees.Apothecary

      }
      noException shouldBe thrownBy { t.mappedValues }
    }
  }
}
