/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
import io.truthencode.ddo.support.requisite._

/**
  *
  * @todo change this to grant (need to create GrantsToRace trait)
  */
trait CompositePlating
    extends FeatRequisiteImpl
    with RaceRequisiteImpl
    with Passive
    with RequiresAttribute
    with GrantsToRace { self: RacialFeat =>
  private def wfTraits = List((Race.Warforged, 1), (Race.Bladeforged, 1))

  override def grantsToRace: Seq[(Race, Int)] = wfTraits
}