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

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.effect.Feature.printFeature
import io.truthencode.ddo.model.feats.GeneralFeat.logger
import io.truthencode.ddo.model.feats.{Feat, GeneralFeat}
import io.truthencode.ddo.model.item.weapon.WeaponCategory.{filterByWeaponClass, icPlus1, icPlus2, icPlus3}
import io.truthencode.ddo.model.item.weapon.WeaponClass
import io.truthencode.ddo.model.skill.Skill.{Listen, Spot}
import io.truthencode.ddo.model.stats.BasicStat
import org.scalatest.TryValues._
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import io.truthencode.ddo.test.tags.{FeatTest, FeatureTest, SkillTest}

import scala.Option
import scala.collection.immutable

class FeatureTest extends AnyFunSpec with Matchers with LazyLogging {

  def fixture = new {
    val sourceInfo: SourceInfo = SourceInfo("TestContext", this)
  }

  def optPlus[T](t: T, n: Int): Option[(T, Int)] = {
    val x = Option(t)
    logger.info(s"Maybe add $n to $x")
    Option(x) match {
      case Some(_) => Some(t, n)
      case _ => None
    }
  }

  val featEffects = {
    val fList = for {
      feat <- Feat.values
      features <- feat.namedFeatures
      feature <- features._2
    } yield (feat, feature)
  }

  describe("A feature") {
    it("should be able to affect a dodge chance ", FeatureTest, FeatTest) {
      val param = EffectParameter.BonusType(BonusType.Feat)
      val part = EffectPart.DodgeChance
      val partMod = new PartModifier[Int, BasicStat] with ParameterModifier[Int, BonusType] {
        lazy override protected[this] val partToModify: BasicStat =
          BasicStat.DodgeChance
        lazy override protected[this] val parameterToModify: BonusType =
          BonusType.Feat
        override val value: Int = 3
        override val source: SourceInfo = SourceInfo("ExampleDodge", this)
      }
      logger.info(s"found Feature Effect with Name ${partMod.name}")
      partMod.parameter.success.value should be(param)
      partMod.part.success.value should be(part)
      //  f.parameter should be a 'success //Be Success(BonusType(ActionBoost)) (EffectParameter.BonusType)
      // f.part should be a 'success //shouldBe (EffectPart.Feat)
      partMod.value shouldEqual 3

    }

    it("Should support Skill Augmentation", FeatTest, SkillTest, FeatureTest) {
      val param = EffectParameter.BonusType(BonusType.Feat)
      val part = EffectPart.Skill
      val feat = GeneralFeat.Alertness
      val ff: immutable.Seq[Feature.SkillEffect] = feat.features.collect { case y: Feature.SkillEffect =>
        y
      }
      ff.map(_.skill) should contain allOf (Listen, Spot)
    }

    it("should contain relevant source information", FeatTest, SkillTest, FeatureTest) {
      val feat = GeneralFeat.Alertness
      val ff: Option[Feature.SkillEffect] = feat.features.collectFirst { case y: Feature.SkillEffect =>
        y
      }
      ff should not be empty
      val effect = ff.get
      val source = effect.source
      source shouldEqual feat
      logger.info(Feature.printFeature(effect))
    }

    it("Should be extractable using a filter like Basic Stat or BonusType", FeatureTest, FeatTest) {
      val param = EffectParameter.BonusType(BonusType.Feat)
      val part = EffectPart.DodgeChance
      val feat = GeneralFeat.Dodge
      feat.features.collectFirst { case y: PartModifier[Int, BasicStat] with ParameterModifier[Int, BonusType] =>
        y
      } match {
        case Some(x) =>
          (x.parameter should be).a(Symbol("success"))
          x.parameter.success.value should be(param)
          (x.part should be).a(Symbol("success"))
          x.part.success.value should be(part)
      }
    }

    it("Should be able to sort a part and parameter with a value") {
      val param = EffectParameter.BonusType(BonusType.ActionBoost)
      val part = EffectPart.Feat(GeneralFeat.Trip)
      val f = fixture
      val pm = new PartModifier[Int, GeneralFeat] with ParameterModifier[Int, BonusType] {
        lazy override protected[this] val partToModify: GeneralFeat =
          GeneralFeat.Trip
        lazy override protected[this] val parameterToModify: BonusType =
          BonusType.ActionBoost
        override val value: Int = 3
        override val source: SourceInfo = f.sourceInfo
      }

      pm.parameter.success.value should be(param)
      pm.part.success.value should be(part)

      pm.value shouldEqual 3

    }
  }
  describe("Improved Critical Features") {
    they("Should be broken down by weapon type") {
      val result = WeaponClass.values.flatMap { weaponClass =>
        filterByWeaponClass(weaponClass).map { weapon =>
          icPlus1 shouldNot be(empty)
          icPlus2 shouldNot be(empty)
          icPlus3 shouldNot be(empty)
          // The item we are looking for is in one of these lists
          val a1 = icPlus1.filter(_ == weapon).flatMap(optPlus(_, 1))
          val a2 = icPlus2.filter(_ == weapon).flatMap(optPlus(_, 2))
          val a3 = icPlus3.filter(_ == weapon).flatMap(optPlus(_, 3))

          val squish = (a1 ++ a2 ++ a3)
          squish should have size 1
          logger.info(s"squish is ${squish.size}")
          val squished = squish.head
          squished._1 shouldEqual weapon
          squished
        }
      }
    }
    they("Should have name") {
      val icFeat = GeneralFeat.ImprovedCritical.subFeats.head
      icFeat.features.foreach { f =>
        logger.info(printFeature(f))
      }
    }
  }

}
