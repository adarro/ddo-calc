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
import io.truthencode.ddo.model.effect.Feature.printFeature
import io.truthencode.ddo.model.feats.GeneralFeat
import io.truthencode.ddo.model.stats.{BasicStat, MissChance}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.language.postfixOps

class EffectPartTest extends AnyFunSpec with Matchers with LazyLogging {
  describe("effectPart") {
    ignore("is") {
      EffectPart.values.foreach { ep =>
        logger.info(s"$ep.name ${ep.entryName}   ${ep.searchPattern()}")
      }
    }

    they("have baked-in search patterns") {
      EffectPart.anyAbilities.foreach { ep =>
        val sp = ep.searchPattern()
        val en = ep.entryName
        logger.info(s"spat => $sp ${ep.searchPattern(ep.entryName)} en $en")
      }
    }

    they("may override naming") {
      val entry = EffectPart.MissChanceEffect(BasicStat.DodgeChance)
      val partToModify: BasicStat with MissChance =
        BasicStat.DodgeChance
      val entry2 = EffectPart.MissChanceEffect(partToModify)
      entry shouldEqual entry2
      entry.entryName shouldEqual "MissChance:Dodge"

    }

    they("should identify and recognize MissChance effects") {

      val eut = GeneralFeat.Dodge
      List(eut).foreach { thingWithFeature =>
        thingWithFeature.features.foreach { f =>
          logger.info(printFeature(f))
        }
      }
    }

    they("should identify and recognize Granted Ability effects") {

      val eut = GeneralFeat.Attack
      List(eut).foreach { thingWithFeature =>
        thingWithFeature.features.foreach { f =>
          logger.info(printFeature(f))
        }
      }
    }

    they("should identify and recognize skill effects") {
      val alertness = GeneralFeat.Alertness
      List(alertness).foreach { thingWithFeature =>
        thingWithFeature.features.foreach { f =>
          logger.info(printFeature(f))
        }
      }
    }
  }

}
