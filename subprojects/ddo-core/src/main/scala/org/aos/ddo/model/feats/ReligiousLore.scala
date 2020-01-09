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
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  * [[http://ddowiki.com/page/Religious_Lore Religious Lore]]
  * This feat grants represents your knowledge of the Religion.
  * Characters with this feat are granted special quest-specific dialog options/object interactions that classes without this feat otherwise could not perform.
  * It may also allow certain skill checks to learn insight into specific situations.
  *
  * Cleric, Favored Soul, Paladin received this feat once for every level.
  * Bard received this feat at level 1, 3, 5, 7, 9 ,11, 13 ,15, 17, 19.
  *
  * Verbatim from the release notes:
  * Many classes now gain the "Arcane Lore", "Religious Lore", or "Wilderness Lore" feats every level,
  * which may modify certain dialog options or come up in other ways during quests.
  */
protected[feats] trait ReligiousLore
    extends FeatRequisiteImpl
    with Passive
    with Active
    with GrantsToClass
    with StackableFeat
    with FreeFeat { self: ClassFeat =>

  private def bardLevels =
    (1 to 20 by 2).toList.map((HeroicCharacterClass.Bard, _))

  private def allLevelsClasses =
    for {
      c <- List(HeroicCharacterClass.Cleric,
                HeroicCharacterClass.FavoredSoul,
                HeroicCharacterClass.Paladin,
                HeroicCharacterClass.FavoredSoul)
      l <- 1 to 20
    } yield (c, l)

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    allLevelsClasses.sortBy(_._1.entryName) ++ bardLevels
}
