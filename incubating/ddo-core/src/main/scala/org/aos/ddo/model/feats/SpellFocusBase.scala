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
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAnyOfClass
}

/** Icon Feat Spell Focus.png
  * Spell Focus - Passive
  * This feat makes it harder for enemies to resist the caster's spells of a particular school by adding +1 to the difficulty class of the spell.
  *
  * Level 1: Artificer, Bard, Cleric, Druid, Favored Soul
  * Level 1: Sorcerer, Wizard; Level 4: Paladin, Ranger
  *
  * @todo create spell focus:X feats
  */
protected[feats] trait SpellFocusBase
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with RequiresAnyOfClass { self: GeneralFeat =>
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
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
