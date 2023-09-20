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
import io.truthencode.ddo.model.classes.HeroicCharacterClass._
import io.truthencode.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfFeat,
  RequiresAnyOfClass
}

/**
 * Usage: Passive Prerequisite: Greater Spell Focus feat of the same spell school and Lv 11
 * Cleric/Druid/Wizard, or Lv 12 Sorcerer/Favored Soul, or Lv 15 Artificer, or Lv 16 Bard
 */
trait EpicSpellFocusBase
  extends FeatRequisiteImpl with ClassRequisiteImpl with SpellCastingPassive with RequiresAnyOfClass
  with RequiresAllOfFeat { self: EpicFeat =>
  private val lvl11: Seq[(HeroicCharacterClass, Int)] = List(Cleric, Druid, Wizard).map((_, 11))
  private val lvl12: Seq[(HeroicCharacterClass, Int)] = List(Sorcerer, FavoredSoul).map((_, 12))
  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    lvl11 ++ lvl12 :+ (Artificer, 15) :+ (Bard, 16)

  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.SpellFocus)
}
