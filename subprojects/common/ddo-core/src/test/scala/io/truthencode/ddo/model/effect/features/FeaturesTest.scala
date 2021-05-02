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
import io.truthencode.ddo.model.effect.{Feature, SourceInfo}
import io.truthencode.ddo.model.feats.GeneralFeat
import org.scalatest.{FunSpec, Matchers}

class FeaturesTest extends FunSpec with Matchers with LazyLogging {
  val dodgeChance = "DodgeChance"
  describe("A Features List") {
    it("Should derive a list of names / ids") {
      val f = GeneralFeat.Dodge
      val m = f.features.flatMap(_.name)

      m should contain(dodgeChance)

    }

    it("Should have a named map list of features by id") {
      val f = GeneralFeat.Dodge
      f.namedFeatures.keys.toStream should contain(dodgeChance)
    }

    it("should contain source information") {
      val d = GeneralFeat.Dodge
      val s: SourceInfo = d.namedFeatures(dodgeChance).head.source
      logger.debug(s"source: $s")
      s.sourceId should startWith("Feat")
      s.sourceId should endWith("Dodge")
      s.sourceRef shouldEqual d
    }
  }

}
