/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: GeneralFeatTest.scala
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
import io.truthencode.ddo.model.effect.Feature.printFeature
import io.truthencode.ddo.model.feats.GeneralFeat.SimpleWeaponProficiency
import io.truthencode.ddo.model.item.weapon.WeaponCategory
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

/**
 * Created by adarr on 2/7/2017.
 */
class GeneralFeatTest extends AnyFunSpec with Matchers with LazyLogging {
  // val logger = LoggerFactory.getLogger(this.getClass)
  describe("General Feats") {
    they("have discrete values") {
      noException should be thrownBy GeneralFeat.values
    }
  }

  describe("Weapon Proficiencies") {
    they("are associated") {
      val wp = WeaponCategory.simpleWeapons
      wp shouldNot be(empty)
      val wc = wp.head

      val cl = GeneralFeat.classSimpleWeaponGrants(wc).flatten.toSeq
      cl shouldNot be(empty)
      val sp = SimpleWeaponProficiency(cl, wc)

    }
    they("should function even with a default (empty) feature set") {

      val f: Seq[(GeneralFeat, Feature[?])] = for
        feat <- GeneralFeat.values
        features <- feat.features
      yield feat -> features

      f.foreach { ff =>
        logger.info(printFeature(ff._2))
      }
    }

//    ignore("Exist") {
//      val swp = GeneralFeat.SimpleWeaponProficiency
//      swp.subFeats.foreach { s =>
//        logger.info(s.displayText)
//      }
//    }
  }
  describe("Toughness") {
    it("has features") {
      GeneralFeat.Toughness.features.foreach { f => logger.info(printFeature(f)) }

    }
  }

}
