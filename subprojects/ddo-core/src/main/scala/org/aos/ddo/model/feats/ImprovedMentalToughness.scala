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
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfFeat,
  RequiresAnyOfClass
}

/** Icon Feat Improved Mental Toughness.png
  * Improved Mental Toughness
  * Passive
  * This feat increases the character maximum spell points by 10 at level 1, and 5 spell points for each additional level.
  *     Also increases your spell critical chance by 1%.
  *     Stacks with Mental Toughness.
  *
  * @todo Need to add ability to cast X Level spells as requisite
  *       Mental Toughness, Ability to cast 3rd level spells
  *       Level 5: Cleric, Druid, Wizard
  *       Level 6: Favored Soul, Sorcerer
  *       Level 7: Artificer, Bard; Level 10: Paladin, Ranger
  */
protected[feats] trait ImprovedMentalToughness
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with RequiresAllOfFeat
    with RequiresAnyOfClass { self: GeneralFeat =>
  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.MentalToughness)

  override def anyOfClass: Seq[(CharacterClass, Int)] =
    List(
      (CharacterClass.Artificer, 7),
      (CharacterClass.Bard, 7),
      (CharacterClass.Cleric, 5),
      (CharacterClass.Druid, 5),
      (CharacterClass.FavoredSoul, 6),
      (CharacterClass.Sorcerer, 6),
      (CharacterClass.Wizard, 5),
      (CharacterClass.Paladin, 10),
      (CharacterClass.Ranger, 10)
    )
}
