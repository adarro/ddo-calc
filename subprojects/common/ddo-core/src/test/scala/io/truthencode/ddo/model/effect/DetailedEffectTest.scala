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
package io.truthencode.ddo.model.effect

import io.truthencode.ddo.api.model.effect.DetailedEffect
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.effect.EffectValidation.*
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import com.typesafe.scalalogging.LazyLogging
import zio.prelude.Validation

import scala.language.postfixOps

class DetailedEffectTest extends AnyFunSpec with Matchers with LazyLogging {
  final val invalidTriggerOn = Seq("Every third Thursday when the moon is full")
  final val invalidTriggerOff = Seq("Prior Relationships")
  final val validTriggerOn = Seq("OnDamage")
  final val validTriggerOff = Seq("OnSpellCast")
  final val someValidTriggerOn = invalidTriggerOn ++ validTriggerOn
  final val validId = "criticalThreatRange"
  final val someDescriptions = "Sample Effect Description"
  final val validCategories = List("MissChance", "Health")
  final val someValidCategories = List("MissChance", "HitChance", "Elephant")
  final val noValidCategories = List("MissSpell", "Laziness")
  final val validBonusType = "Dodge"
  final val invalidBonusType = "SpontaneousErection" // Rogue 'Builder' :)

  val aPerfectlyValidDetailEffect: DetailedEffect =
    DetailedEffect(validId, someDescriptions, validTriggerOn, validTriggerOff, validBonusType)

  def duplicator(
    id: String,
    description: String,
    triggersOn: Seq[String],
    triggersOff: Seq[String],
    bonusType: String): (DetailedEffect, Validation[String, DetailedEffect]) = {
    (
      DetailedEffect(id, description, triggersOn, triggersOff, bonusType),
      validateDetailedEffect(id, description, triggersOn, triggersOff, bonusType))
  }

  describe("A detailed effect") {

    they("should allow fully validated effects") {

      val criticalThreatRangeType = BonusType.Feat.entryName

      val (effectDetail, result) = duplicator(
        validId,
        someDescriptions,
        validTriggerOn,
        validTriggerOff,
        criticalThreatRangeType)

      val r = result.fold(xml => xml.toString, xml => xml.toString)
      logger.info(s"Result: $r")
      result.isSuccess shouldBe true

      result.toOption shouldBe Some(effectDetail)

    }

    they("should fail invalid trigger names") {
      val (invalidTriggerOnEffectName, validatorTOn) =
        duplicator(validId, someDescriptions, invalidTriggerOn, validTriggerOff, validBonusType)

      validatorTOn.isSuccess shouldBe false

      val (invalidTriggerOffEffectName, validatorTOff) =
        duplicator(validId, someDescriptions, invalidTriggerOn, validTriggerOff, validBonusType)

      validatorTOff.isSuccess shouldBe false

    }

    they("should filter out unknown triggers") {
      val (someValidTrig, result) =
        duplicator(validId, someDescriptions, someValidTriggerOn, validTriggerOff, validBonusType)
      result.isSuccess shouldBe true
      // objects will be different because invalid triggers are filtered out
//      result.toOption shouldBe Some(someValidTrig)
    }
  }

}
