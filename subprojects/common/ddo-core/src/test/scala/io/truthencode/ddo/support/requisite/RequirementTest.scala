/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: RequirementTest.scala
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
import io.truthencode.ddo.model.feats.GeneralFeat
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class RequirementTest extends AnyFunSpec with Matchers with LazyLogging {

  describe("Something with Requisites") {
    they("Should be readable") {
      //  val enumList = Seq(GeneralFeat.Alertness, GeneralFeat.Manyshot, GeneralFeat.Dodge)
      val enumList = Seq(GeneralFeat.Manyshot)
      val values = for
        f <- enumList.collect { case x: (RequisiteExpression & RequisiteType & Inclusion) => x }
        pr <- f.prerequisites
        if f.prerequisites.nonEmpty
      yield ValueWithRequirements(f, pr.reqType, pr.incl, pr.groupKey, pr.req)
      val sor = SemiOrderedRequirements(values*)
      sor.displayPrerequisites.foreach { dp =>
        logger.info(dp)
      }
      sor.displayBonusSelections.foreach(dp => logger.info(dp))
      sor.displayGranted.foreach(dp => logger.info(dp))
//      logger.info(values.size.toString)
//      values.foreach { v =>
//        logger.info(s"${v.f.entryName} ${v.req.size} requirements")
//        v.req.foreach { r =>
//          logger.info(
//            s"${v.groupKey} ${r.displayText} ${v.requisiteType}: ${v.includeType} ${r.entryName} ")
//        }
//      }
    }
  }

}
