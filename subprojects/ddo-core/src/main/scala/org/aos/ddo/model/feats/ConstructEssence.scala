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
package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Artificer
import org.aos.ddo.model.race.Race
import org.aos.ddo.model.race.Race.Warforged
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RaceRequisiteImpl, RequiresAllOfClass, RequiresNoneOfRace}

/**
  * Created by adarr on 4/5/2017.
  */
protected[feats] trait ConstructEssence extends FeatRequisiteImpl
  with RaceRequisiteImpl
  with RequiresAllOfClass
  with RequiresNoneOfRace
  with ArtificerBonusFeat
  with Passive {
  self: ClassFeat =>
  override def noneOfRace: Seq[(Race, Int)] = List((Warforged, 1))

  override def allOfClass: Seq[(CharacterClass, Int)] = List((Artificer, 3))
}
