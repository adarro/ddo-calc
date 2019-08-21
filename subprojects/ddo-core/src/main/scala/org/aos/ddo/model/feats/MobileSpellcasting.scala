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
import org.aos.ddo.support.requisite._

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

  override def anyOfClass: Seq[(CharacterClass, Int)] =
    List(
      (CharacterClass.Artificer, 3),
      (CharacterClass.Bard, 4),
      (CharacterClass.Cleric, 3),
      (CharacterClass.Druid, 3),
      (CharacterClass.FavoredSoul, 4),
      (CharacterClass.Sorcerer, 4),
      (CharacterClass.Wizard, 3),
      (CharacterClass.Paladin, 7),
      (CharacterClass.Ranger, 7)
    )
}
