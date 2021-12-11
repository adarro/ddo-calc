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

import org.scalatest.funspec.AnyFunSpec
import io.truthencode.ddo.model.stats.Category
import com.wix.accord.dsl._
import com.wix.accord._
import com.wix.accord.scalatest.ResultMatchers
import io.truthencode.ddo.model.effect.Validation._
import org.scalatest.matchers.should.Matchers

import scala.language.postfixOps

class DetailedEffectTest extends AnyFunSpec with Matchers with ResultMatchers {
  final val invalidTriggerOn = "Every third Thursday when the moon is full"
  final val invalidTriggerOff = "Prior Relationships"
  final val validTriggerOn = "OnDamage"
  final val validTriggerOff = "OnSpellCast"
  final val validId = "criticalThreatRange"
  final val someDescriptions = "Sample Effect Description"
  final val validCategories = List("MissChance", "Health")
  final val someValidCategories = List("MissChance", "HitChance", "Elephant")
  final val noValidCategories = List("MissSpell", "Laziness")

  val aPerfectlyValidDetailEffect: DetailedEffect =
    DetailedEffect(validId, someDescriptions, validCategories, validTriggerOn, validTriggerOff)

  describe("A detailed effect") {
    they("should invalidate non-existent categories") {
      val someInvalidCategoriesEffect: DetailedEffect =
        aPerfectlyValidDetailEffect.copy(categories = someValidCategories)
      val noValidCategoryEffect = aPerfectlyValidDetailEffect.copy(categories = noValidCategories)

      val result = validate(someInvalidCategoriesEffect)
      val noCatResult = validate(noValidCategoryEffect)
      result should be(aFailure)
      noCatResult should be(aFailure)
    }

    it("should allow fully validated effects") {
      assume(TriggerEvent.withNameOption(validTriggerOn).isDefined)
      val result = validate(aPerfectlyValidDetailEffect)
      result should be(aSuccess)
    }

    they("should disallow invalid trigger names") {
      val invalidTriggerOnEffectName =
        DetailedEffect(validId, someDescriptions, validCategories, invalidTriggerOn, validTriggerOff)
      val invalidTriggerOffEffectName =
        DetailedEffect(validId, someDescriptions, validCategories, invalidTriggerOn, validTriggerOff)
      val result = validate(invalidTriggerOnEffectName)
      val result2 = validate(invalidTriggerOffEffectName)
      result should be(aFailure)
      result2 should be(aFailure)
    }
  }

}
