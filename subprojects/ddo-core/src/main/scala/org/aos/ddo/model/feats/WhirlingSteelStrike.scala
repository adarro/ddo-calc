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
import org.aos.ddo.support.requisite.{ClassRequisiteImpl, FeatRequisiteImpl, RequiresAllOfClass}

trait WhirlingSteelStrike extends FeatRequisiteImpl
  with ClassRequisiteImpl
  with Passive
  with RequiresAllOfClass
  with FighterBonusFeat
  with MartialArtsFeat {
  self: GeneralFeat =>
  override def allOfFeats: Seq[Feat] = Seq(Feat.withName("Weapon Focus: Slashing"))

  override def anyOfFeats: Seq[Feat] = Seq(Feat.withName("Proficiency: Longswords"), DeityFeat.FollowerOfTheSovereignHost, RacialFeat.HalfElfDilettanteFighter)

  override def allOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Monk, 1))
}
