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
package io.truthencode.ddo.support.requisite

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Ranger
import io.truthencode.ddo.model.feats.GeneralFeat
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.requisite.RequirementImplicits.{ClassImplicits, FeatImplicits}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class packageTest extends AnyFunSpec with Matchers with LazyLogging {
  describe("class to Requisite") {
    it("should convert a class requirement to a Requisite case class") {
      val grantToClass: Seq[(HeroicCharacterClass, Int)] = {
        List((Ranger, 6))
      }
      val gtc = grantToClass.map(_.toReq)
      val hc = grantToClass.head._1
      val hWithSplit = hc.entryName.splitByCase.toPascalCase
      val hOpt = Requirement.withNameOption(hc.entryName)
      val nMap = Requirement.namesToValuesMap
      val definedName = s"${HeroicCharacterClass.searchPrefix}${hc.entryName}"
      val dOpt = Requirement.withNameOption(definedName)
      val readName = Requirement.values.filter(_.entryName.contains("Ranger")).head.entryName
      definedName shouldEqual readName
      gtc should have size 1
    }
  }
  describe("Feat to requisite") {
    it("should convert a feat requirement to a Requirement Case Class ") {
      def allOfFeats: Seq[GeneralFeat] =
        List(GeneralFeat.PointBlankShot, GeneralFeat.RapidShot)
      val aof = allOfFeats.map(_.toReq)
      aof should not be empty
    }
  }
}
