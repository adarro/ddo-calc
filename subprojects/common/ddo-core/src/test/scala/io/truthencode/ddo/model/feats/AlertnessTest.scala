/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: AlertnessTest.scala
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
package io.truthencode.ddo.model.feats

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.effect.Feature
import io.truthencode.ddo.model.skill.Skill
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class AlertnessTest extends AnyFunSpec with Matchers with LazyLogging {
  describe("the feat Alertness") {
    it("should provide a +2 bonus to the listen and spot skills") {
      val al = GeneralFeat.Alertness
      val listen = Skill.Listen.withPrefix
      val spot = Skill.Spot.withPrefix
      //  al.namedFeatures(listen) should be('defined)
      al.features.size shouldBe 2
      val named = al.namedFeatures.keys.toList
      (named should contain).allOf("Skill:Listen", "Skill:Spot")
      val lf = al.namedFeatures(listen).head
      val sf = al.namedFeatures(spot).head
      lf.value shouldBe 2
      sf.value shouldBe 2
      logger.debug(Feature.printFeature(lf))
      // al.namedFeatures.size shouldBe 2
//      val l =al.namedFeatures(listen).head
//      l.value shouldBe 2
    }

    it("should be available as a feature set or directly from the feat") {

      val fsAlert = Feat.featureSet.filter(_._1 == GeneralFeat.Alertness)
      logger.info(s"Alertness: ${fsAlert.size}")
    }
  }
  describe("A feature set") {
    it("should be available via named features") {
      /*
             we want to mirror and evaluate
               container <- source.values
               features <- container.namedFeatures
               feature <- features._2
       */
      val container = Feat.values.filter(_ == GeneralFeat.Alertness)
      val featuresFM = container.flatMap(_.namedFeatures)
      featuresFM.size shouldBe 2
      featuresFM.foreach { m => logger.info(s"feature set ${m._1} => ${m._2}") }
      val features = container.map(_.namedFeatures)
      features.size shouldBe 1
      val feature = features.head
      feature.size shouldBe 2

      logger.info(s"Alertness: ${features.size}")
    }
    it("does stuff") {
      val r = Feat.values.map { container =>
        container.features.map(f => f.name -> f.value)
      }
    }
  }
}
