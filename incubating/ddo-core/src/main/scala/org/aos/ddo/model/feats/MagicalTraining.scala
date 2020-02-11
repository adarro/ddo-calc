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

import org.aos.ddo.model.classes.HeroicCharacterClass
import org.aos.ddo.model.classes.HeroicCharacterClass._
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  * This feat increases the maximum spell points by 80,
  * increases the spell critical chance by 5% and grants Echoes of Power if the spell points pool drops below 12.
  *
  * @@note granted Level 1 : Cleric, Druid, Favored Soul, Artificer, Sorcerer, Wizard, Warlock
  *     granted via enhancement for:
  *     Bard - third rank of Magical Studies from Spellsinger enhancements
  *     Ranger - third rank of Energy of the Wild from Arcane Archer enhancements
  *
  *     Any other class as a trainable feat
  */
protected[feats] trait MagicalTraining
    extends FeatRequisiteImpl
    with Passive
    with FreeFeat
    with ClassRequisiteImpl
    with GrantsToClass { self: GeneralFeat =>
  private def magicClasses =
    List(Cleric, Druid, FavoredSoul, Artificer, Sorcerer, Wizard, Warlock)
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    magicClasses.map((_, 1))
}
