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

import io.truthencode.ddo.model.race.Race
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.Prefix
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RaceRequisite, RequiresAllOfRace}

/**
 * Created by adarr on 2/20/2017.
 */
trait HalfOrcBlood
  extends FeatRequisiteImpl with RaceRequisite with Passive with Prefix with RequiresAllOfRace {
  self: RacialFeat =>

  /**
   * Delimits the prefix and text.
   */
  override protected val prefixSeparator: String = " "

  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfOrc, 1))

  override def grantsToRace: Seq[(Race, Int)] = List((Race.HalfOrc, 1))

  override def prefix: Option[String] = Some("Half-Orc")

  override protected def nameSource: String = "Orc Blood".toPascalCase
}
