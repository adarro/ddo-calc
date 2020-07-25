/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAnyOfClass}

/**
  * Created by adarr on 2/21/2017.
  *
  * @todo Need to add requirement to cast 2nd level spells
  */
trait HeightenSpell
    extends FeatRequisiteImpl
    with MetaMagic
    with RequiresAnyOfClass
    with ArtificerBonusFeat
    with AlchemistBonusFeat {
  self: MetaMagicFeat =>

  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    List(
      (HeroicCharacterClass.Artificer, 4),
      (HeroicCharacterClass.Bard, 4),
      (HeroicCharacterClass.Cleric, 3),
      (HeroicCharacterClass.Druid, 1),
      (HeroicCharacterClass.FavoredSoul, 4),
      (HeroicCharacterClass.Sorcerer, 4),
      (HeroicCharacterClass.Warlock, 4),
      (HeroicCharacterClass.Wizard, 3),
      (HeroicCharacterClass.Paladin, 8),
      (HeroicCharacterClass.Ranger, 8)
    )
}
