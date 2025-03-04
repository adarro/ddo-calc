/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: DetailedEffectTest.scala
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

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.api.model.effect.{DetailedEffect, ScalingEffect, ScalingInfo}
import io.truthencode.ddo.model.effect.EffectValidation.*
import io.truthencode.ddo.support.validation.{invalidationOptions, validateDetailedEffect}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
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
  final val emptyScalingMap: Option[Map[String, Int]] = None
  final val emptyScalingInfo: Option[Seq[ScalingInfo]] = None
  final val validSimpleScalingMap = Some(Map("SpellPower" -> 100))

  val aPerfectlyValidDetailEffect: DetailedEffect =
    DetailedEffect(validId, someDescriptions, validTriggerOn, validTriggerOff, validBonusType)
  given filterStrategy: invalidationOptions = invalidationOptions.FilterValid

  def duplicator(
    id: String,
    description: String,
    triggersOn: Seq[String],
    triggersOff: Seq[String],
    bonusType: String,
    scaling: Option[Map[String, Int]]): (DetailedEffect, Validation[String, DetailedEffect]) = {
    val mapToInfo: Option[Seq[ScalingInfo]] = scaling match
      case Some(value) =>
        Some(value.map { si =>
          ScalingEffect(si._2, si._1)
        }.toSeq)
      case None => None

    (
      DetailedEffect(id, description, triggersOn, triggersOff, bonusType),
      validateDetailedEffect(id, description, triggersOn, triggersOff, bonusType, mapToInfo))
  }

  describe("A detailed effect") {

    they("should allow fully validated effects") {
      val (effectDetail, result) = duplicator(
        validId,
        someDescriptions,
        validTriggerOn,
        validTriggerOff,
        validBonusType,
        validSimpleScalingMap)
      logger.info("evaluating result")
      //      val r = result.fold(xml => xml.toString, xml => xml.toString)
      //      logger.info(s"Result: $r")
      result.isSuccess shouldBe true

    }
  }

  describe("A different section") {
    they("should fail invalid triggerOff names") {
      val (invalidTriggerOnEffectName, validatorTOn) =
        duplicator(
          validId,
          someDescriptions,
          invalidTriggerOn,
          validTriggerOff,
          validBonusType,
          validSimpleScalingMap)

      validatorTOn.isFailure shouldBe true
    }

    they("should fail on invalid triggerOff") {
      val (invalidTriggerOffEffectName, validatorTOff) =
        duplicator(
          validId,
          someDescriptions,
          validTriggerOn,
          invalidTriggerOff,
          validBonusType,
          validSimpleScalingMap)

      validatorTOff.isFailure shouldBe true

    }

    they("should fail on invalid bonus type") {
      val (invalidTriggerOffEffectName, validatorTOff) =
        duplicator(
          validId,
          someDescriptions,
          validTriggerOn,
          validTriggerOff,
          invalidBonusType,
          validSimpleScalingMap)
      val vErr: String = validatorTOff.fold(x => x.toString, y => "woohoo")
      logger.error(s"bonus type should have failed with $vErr")
      validatorTOff.isFailure shouldBe true

    }

    they("should filter out unknown triggers") {
      logger.info(s"someValidTriggerOn $someValidTriggerOn")
      val (someValidTrig, result) =
        duplicator(
          validId,
          someDescriptions,
          someValidTriggerOn,
          validTriggerOff,
          validBonusType,
          validSimpleScalingMap)
      result.isSuccess shouldBe true
    }
  }

}
