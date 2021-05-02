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
import io.truthencode.ddo.support.requisite._

/** Icon Feat Mobile Spellcasting.png
  * Mobile Spellcasting
  * Passive
  * Character while moving cast at half their normal movement speed, however with Mobile Spellcasting, character can move at full speed.
  *
  * Combat Casting, Dexterity 13,
  * Ability to cast 2nd level spells
  * Level 3: Artificer, Cleric, Druid, Wizard
  * Level 4: Bard, Favored Soul, Sorcerer
  * Level 7: Paladin, Ranger
  *
  * @todo Add ability to cast 2nd level spells req
  */
protected[feats] trait MobileSpellcasting
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with RequiresAllOfFeat
    with RequiresAttribute
    with RequiresAnyOfClass { self: GeneralFeat =>
  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.CombatCasting)

  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    List(
      (HeroicCharacterClass.Artificer, 3),
      (HeroicCharacterClass.Bard, 4),
      (HeroicCharacterClass.Cleric, 3),
      (HeroicCharacterClass.Druid, 3),
      (HeroicCharacterClass.FavoredSoul, 4),
      (HeroicCharacterClass.Sorcerer, 4),
      (HeroicCharacterClass.Wizard, 3),
      (HeroicCharacterClass.Paladin, 7),
      (HeroicCharacterClass.Ranger, 7)
    )
}
