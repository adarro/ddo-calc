/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ImprovedMentalToughness.scala
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

import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfFeat,
  RequiresAnyOfClass
}

/**
 * Icon Feat Improved Mental Toughness.png Improved Mental Toughness Passive This feat increases the
 * character maximum spell points by 10 at level 1, and 5 spell points for each additional level.
 * Also increases your spell critical chance by 1%. Stacks with Mental Toughness.
 *
 * @todo
 *   Need to add ability to cast X Level spells as requisite Mental Toughness, Ability to cast 3rd
 *   level spells Level 5: Cleric, Druid, Wizard Level 6: Favored Soul, Sorcerer Level 7: Artificer,
 *   Bard; Level 10: Paladin, Ranger
 */
protected[feats] trait ImprovedMentalToughness
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with ClassRequisiteImpl with Passive
  with RequiresAllOfFeat with RequiresAnyOfClass with AlchemistBonusFeat { self: GeneralFeat =>
  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.MentalToughness)

  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    List(
      (HeroicCharacterClass.Artificer, 7),
      (HeroicCharacterClass.Bard, 7),
      (HeroicCharacterClass.Cleric, 5),
      (HeroicCharacterClass.Druid, 5),
      (HeroicCharacterClass.FavoredSoul, 6),
      (HeroicCharacterClass.Sorcerer, 6),
      (HeroicCharacterClass.Wizard, 5),
      (HeroicCharacterClass.Paladin, 10),
      (HeroicCharacterClass.Ranger, 10),
      (
        HeroicCharacterClass.Alchemist,
        7
      ) // need to verify, not listed in wiki detail but found under Alchemist bonus feats category
    )
}
