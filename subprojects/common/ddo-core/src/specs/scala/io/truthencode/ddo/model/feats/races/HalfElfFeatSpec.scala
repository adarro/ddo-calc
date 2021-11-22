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
package io.truthencode.ddo.model.feats.races

import java.util

import io.truthencode.ddo.model.feats.{Feat, FeatDisplayHelper, RaceSupport}
import io.truthencode.ddo.model.race.Race
import io.truthencode.ddo.support.requisite.RequirementOption
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

import scala.collection.JavaConverters._

@RunWith(classOf[ConcordionRunner])
class HalfElfFeatSpec extends FeatDisplayHelper with RaceSupport {

  override val raceId: Race = Race.HalfElf

  def verifyNonDilettante(): util.List[String] = verifyGrantedFeats
//    Feat.featsFromRace(Race.HalfElf)
//      .filter { x =>
//        !x.isSubFeat && !x.entryName.contains("Dilettante")
//      }
//      .map { x =>
//        x.displayText
//      }
//      .toList
//      .sorted
//      .asJava

  def verifyDilettante(): util.List[String] =
    Feat
      .featsFromRace(Race.HalfElf, RequirementOption.Available)
      .filter { x =>
        !x.isSubFeat && x.entryName.contains("Dilettante")
      }
      .map { x =>
        x.displayText
      }
      .toList
      .sorted
      .asJava

  override val enum: E = Feat
}
