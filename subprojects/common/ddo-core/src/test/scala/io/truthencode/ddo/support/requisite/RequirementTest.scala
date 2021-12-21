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
import enumeratum.EnumEntry
import io.truthencode.ddo.model.feats.GeneralFeat
import io.truthencode.ddo.support.requisite.Requirement._
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class RequirementTest extends AnyFunSpec with Matchers with LazyLogging {
  describe("Something with Requisites") {
    they("Should be readable") {
      val enumList = Seq(GeneralFeat.Manyshot)
      val values = for {
        f <- enumList.collect { case x: RequisiteExpression with RequisiteType with Inclusion => x }
        pr <- f.prerequisites
        if f.prerequisites.nonEmpty
      } yield ValueWithRequirements(f, pr.reqType, pr.incl, pr.req)

      logger.info(values.size.toString)
      values.foreach { v =>
        logger.info(s"${v.f.entryName} ${v.req.size} requirements")
        v.req.foreach { r =>
          val req = r match {
            case ReqCharacterLevel(x) => x
            case ReqFeat(x) => x
            case ReqRace(id, level) => s"$id : $level"
            case ReqClass(id, level) => s"$id : $level"
            case ReqClassEnhancement(id) => id
            case ReqSkill(id, amount, trained) => s"$id $amount : $trained"
            case ReqBaseAttackBonus(amount) => amount
            case ReqAttribute(id, level) => s"$id : $level"
            case _ => "Other"

          }
          val ss = "lsdkf"
          logger.info(s"${v.requisiteType}: ${v.includeType} ${r.entryName} $req ")
        }
      }
    }
  }

  case class ValueWithRequirements(
    f: EnumEntry with RequisiteExpression,
    reqType: RequisiteType,
    incl: Inclusion,
    req: Seq[Requirement]) {
    val requisiteType: String = reqType.requisiteType

    val includeType: String = incl.inclusionType
  }
}
