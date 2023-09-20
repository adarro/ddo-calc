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
package io.truthencode.ddo.model.effect.features

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.effect.Feature.printFeature
import io.truthencode.ddo.model.effect.{Feature, SourceInfo}
import io.truthencode.ddo.model.feats.{Feat, GeneralFeat}
import io.truthencode.ddo.model.stats.BasicStat
import org.scalatest.OptionValues.convertOptionToValuable
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers.defined
import org.scalatest.matchers.should.Matchers

import scala.language.postfixOps

class FeaturesTest extends AnyFunSpec with Matchers with LazyLogging {
  private val dodgeChance = BasicStat.DodgeChance.withPrefix // "DodgeChance"

  describe("A Features List") {
    it("Should be magical") {
      import Features.FeatureExtractor
      val fs = Feat.featureSet
      val fm = Feat.featureMap
      fs.size shouldEqual fm.size

      fm.foreach { m =>
        {
          val eId = m._1.entryName
          val fId = m._2.name
          val pf = printFeature(m._2)
          val ed = m._2.effectDetail
          logger.info(s"\nEntity: $eId, Feature: $fId \n$pf\n$ed")
        }

      }
//      val irslt = for {
//        (k, v) <- fm
//      } yield (k, v.name -> (v, v.effectDetail))

    }

    it("Should derive a list of names / ids") {
      val f = GeneralFeat.Dodge
      val m = f.features.flatMap(_.nameOption)

      m should contain(dodgeChance)

    }

    it("Should have a named map list of features by id") {
      val f = GeneralFeat.Dodge
      f.namedFeatures.keys.to(LazyList) should contain(dodgeChance)
    }

    it("should contain source information") {
      val d = GeneralFeat.Dodge
      d.namedFeatures.foreach(f => logger.info(s"${f._1} ${f._2}"))
      val nf = d.namedFeatures(dodgeChance).headOption
      val s: SourceInfo = nf.value.source
      val v = nf.value
      logger.info(Feature.printFeature(nf.value))
      logger.debug(s"source: $s")
      s.sourceId should startWith("Feat")
      s.sourceId should endWith("Dodge")
      s.sourceRef shouldEqual d
      v.value shouldEqual d.dodgeBonusAmount
    }

    it("Can be converted to a FullEffect") {
      val f = GeneralFeat.Dodge
      val fe = f.features.map(_.asFullEffect)
      fe.foreach { ef =>
        logger.info(ef.toString)
      }
    }

    it("Can emit effects") {
      val f = GeneralFeat.Cleave
      f.effects.foreach { ef =>
        val i = ef.intValue
        ef.intValue shouldBe empty
        logger.info(ef.toString)
      }
    }
  }

}
