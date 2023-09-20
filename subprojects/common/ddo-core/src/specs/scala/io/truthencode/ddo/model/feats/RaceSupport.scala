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
package io.truthencode.ddo.model.feats

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.race.Race
import io.truthencode.ddo.support.requisite.RequirementOption

import java.util
import scala.jdk.CollectionConverters.SeqHasAsJava

/**
 * Created by adarr on 2/19/2017.
 */
trait RaceSupport extends LazyLogging {
  val raceId: Race
  implicit class Util(source: Seq[Feat]) {
    def toJava: util.List[String] = {
      source.map(_.displayText).toList.sorted.asJava
    }
  }

  def verifyGrantedFeats: util.List[String] = {
    val f = Feat
      .featsFromRace(raceId, RequirementOption.AutoGrant)
      .toJava

    logger.info(s"verify ${RequirementOption.AutoGrant.entryName} racial feats located ${f
        .size()} values for Race $raceId ")
    logger.debug(s"values: $f")
    f
  }

  def verifyAvailableFeats: util.List[String] = {
    val f = Feat
      .featsFromRace(raceId, RequirementOption.Available)
      .toJava

    logger.info(s"verify ${RequirementOption.Available.entryName} racial feats located ${f
        .size()} values for Race $raceId ")
    logger.debug(s"values: $f")
    f
  }
}
