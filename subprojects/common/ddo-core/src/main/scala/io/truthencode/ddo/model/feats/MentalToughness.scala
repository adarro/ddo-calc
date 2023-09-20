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

import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAnyOfClass
}

/**
 * Icon Feat Mental Toughness.png Mental Toughness Passive This feat Increases the character maximum
 * spell points by 10 at level 1, and 5 spell points for each additional level. Also increases your
 * spell critical chance by 1%.
 *
 * @todo
 *   Flag Wizard Bonus Feat Level 1: Artificer, Bard, Cleric, Druid, Favored Soul Level 1: Sorcerer,
 *   Wizard; Level 4: Paladin, Ranger
 */
trait MentalToughness
  extends FeatRequisiteImpl with ClassRequisiteImpl with Passive with RequiresAnyOfClass
  with AlchemistBonusFeat {
  self: GeneralFeat =>

  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    List(
      (HeroicCharacterClass.Artificer, 1),
      (HeroicCharacterClass.Bard, 1),
      (HeroicCharacterClass.Cleric, 1),
      (HeroicCharacterClass.Druid, 1),
      (HeroicCharacterClass.FavoredSoul, 1),
      (HeroicCharacterClass.Sorcerer, 1),
      (HeroicCharacterClass.Wizard, 1),
      (HeroicCharacterClass.Paladin, 4),
      (HeroicCharacterClass.Ranger, 4)
    )
}
