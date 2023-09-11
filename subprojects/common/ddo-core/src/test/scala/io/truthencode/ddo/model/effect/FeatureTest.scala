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
import io.truthencode.ddo.api.model.effect.DetailedEffect
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.effect.Feature.printFeature
import io.truthencode.ddo.model.effect.features.SkillEffect
import io.truthencode.ddo.model.feats.{Feat, GeneralFeat}
import io.truthencode.ddo.model.item.weapon.WeaponCategory.{
  filterByWeaponClass,
  icPlus1,
  icPlus2,
  icPlus3
}
import io.truthencode.ddo.model.item.weapon.WeaponClass
import io.truthencode.ddo.model.skill.Skill.{Listen, Spot}
import io.truthencode.ddo.model.stats.{BasicStat, MissChance}
import io.truthencode.ddo.support.naming.UsingSearchPrefix
import io.truthencode.ddo.test.tags.{FeatTest, FeatureTest, SkillTest}
import org.scalatest.TryValues._
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar

import scala.collection.immutable
import scala.util.{Success, Try}

class FeatureTest extends AnyFunSpec with Matchers with MockitoSugar with LazyLogging {

  val featEffects: Unit = {
    val fList = for {
      feat <- Feat.values
      features <- feat.namedFeatures
      feature <- features._2
    } yield (feat, feature)
  }

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

  describe("A feature") {
    it("should be able to affect a dodge chance ", FeatureTest, FeatTest) {
      val param = EffectParameter.BonusType(BonusType.Feat)
      val part = EffectPart.MissChanceEffect(BasicStat.DodgeChance)
      val mockDetailedEffect = mock[DetailedEffect]
      val partMod = new PartModifier[Int, BasicStat with MissChance] with UsingSearchPrefix {

        /**
         * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from
         * qualified "Race:HalfElf"
         *
         * @return
         *   A default or applied prefix
         */
        override def searchPrefixSource: String = partToModify.searchPrefixSource
        override lazy val part: Try[EffectPart] = Success(EffectPart.MissChanceEffect(partToModify))

        /**
         * The General Description should be just that. This should not include specific values
         * unless all instances will share that value. I.e. a Dodge Effect might state it increases
         * your miss-chance, but omit any value such as 20%. Those values will be displayed in the
         * effectText of a specific implementation such as the Dodge Feat or Uncanny Dodge
         */
        override val generalDescription: String =
          "Increases your chance to completely dodge an attack"

        /**
         * a list of Categories useful for menu / UI placement and also for searching / querying for
         * Miss-Chance or other desired effects.
         *
         * This list might be constrained or filtered by an Enumeration or CSV file. The goal is to
         * enable quick and advanced searching for specific categories from general (Miss-Chance) to
         * specific (evasion). In addition, it may be useful for deep searching such as increasing
         * Spot, which should suggest not only +Spot items, but +Wisdom or eventually include a feat
         * or enhancement that allows the use of some other value as your spot score.
         */
        override def categories: Seq[String] =
          Seq(EffectCategories.General, EffectCategories.MissChance).map(_.toString)

        override protected[this] lazy val partToModify: BasicStat with MissChance =
          BasicStat.DodgeChance
        private val eb = EffectParameterBuilder()
          .toggleOffValue(TriggerEvent.OnDeath)
          .toggleOnValue(TriggerEvent.Passive)
          .addBonusType(BonusType.Feat)
          .build

        override protected[this] def effectParameters: Seq[ParameterModifier[_]] = eb.modifiers
        override val effectDetail: DetailedEffect = mockDetailedEffect
        override val value: Int = 3
        override val source: SourceInfo = SourceInfo("ExampleDodge", this)
      }

      logger.info(s"found Feature Effect with Name ${partMod.name}")
      partMod.parameters should contain(Success(param))
      // partMod.parameters.foreach(_.success.value should be(param))
      partMod.part.success.value should be(part)
      //  f.parameter should be a 'success //Be Success(BonusType(ActionBoost)) (EffectParameter.BonusType)
      // f.part should be a 'success //shouldBe (EffectPart.Feat)
      partMod.value shouldEqual 3

    }

    it("Should support Skill Augmentation", FeatTest, SkillTest, FeatureTest) {
      val param = EffectParameter.BonusType(BonusType.Feat)
      // val part = EffectPart.SkillPart
      val feat = GeneralFeat.Alertness
      val ff: immutable.Seq[SkillEffect] = feat.features.collect { case y: SkillEffect =>
        y
      }
      (ff.map(_.skill) should contain).allOf(Listen, Spot)
    }

    it("should contain relevant source information", FeatTest, SkillTest, FeatureTest) {
      val feat = GeneralFeat.Alertness
      val ff: Option[SkillEffect] = feat.features.collectFirst { case y: SkillEffect =>
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
      val part = EffectPart.MissChanceEffect(BasicStat.DodgeChance)
      val feat = GeneralFeat.Dodge
      feat.features.collectFirst { case y: PartModifier[Int, BasicStat] =>
        y
      } match {
        case Some(x) =>
          x.parameters.foreach(_ should be a Symbol("Success"))
          x.parameters
            .flatten(_.toOption)
            .filter(_.entryName.contains("Bonus"))
            .map(_ should be(param))

          (x.part should be).a(Symbol("success"))
          x.part.success.value should be(part)
      }
    }

    it("Should be able to sort a part and parameter with a value") {
      val param = EffectParameter.BonusType(BonusType.ActionBoost)
      val part = EffectPart.Feat(GeneralFeat.Trip)
      val mDetail = mock[DetailedEffect]
      val f = fixture
      val pm = new PartModifier[Int, GeneralFeat] with UsingSearchPrefix {
        override protected[this] lazy val partToModify: GeneralFeat =
          GeneralFeat.Trip

        /**
         * The General Description should be just that. This should not include specific values
         * unless all instances will share that value. I.e. a Dodge Effect might state it increases
         * your miss-chance, but omit any value such as 20%. Those values will be displayed in the
         * effectText of a specific implementation such as the Dodge Feat or Uncanny Dodge
         */
        override val generalDescription: String = "Not an ability to do illegal drugs"

        /**
         * a list of Categories useful for menu / UI placement and also for searching / querying for
         * Miss-Chance or other desired effects.
         *
         * This list might be constrained or filtered by an Enumeration or CSV file. The goal is to
         * enable quick and advanced searching for specific categories from general (Miss-Chance) to
         * specific (evasion). In addition, it may be useful for deep searching such as increasing
         * Spot, which should suggest not only +Spot items, but +Wisdom or eventually include a feat
         * or enhancement that allows the use of some other value as your spot score.
         */
        override def categories: Seq[String] = Seq(EffectCategories.SpecialAttack).map(_.toString)

        /**
         * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from
         * qualified "Race:HalfElf"
         *
         * @return
         *   A default or applied prefix
         */
        override def searchPrefixSource: String = GeneralFeat.searchPrefixSource

        override lazy val part: Try[EffectPart] = Success(EffectPart.Feat(partToModify))
        private val eb = EffectParameterBuilder()
          .toggleOffValue(TriggerEvent.OnAttackRoll)
          .toggleOnValue(TriggerEvent.OnRest)
          .addBonusType(BonusType.ActionBoost)
          .build

        override protected[this] def effectParameters: Seq[ParameterModifier[_]] = eb.modifiers
        override val value: Int = 3
        override val source: SourceInfo = f.sourceInfo
        override val effectDetail: DetailedEffect = mDetail
      }
      val bonuses: Seq[EffectParameter] =
        pm.parameters.flatMap(_.toOption).filter(_.entryName.contains("Bonus"))
      bonuses should contain(param)
      //   pm.parameters.foreach(_.success.value should be(param))
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

          val squish = a1 ++ a2 ++ a3
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
