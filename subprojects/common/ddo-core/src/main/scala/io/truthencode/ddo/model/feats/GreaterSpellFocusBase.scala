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

import io.truthencode.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfFeat,
  RequiresAnyOfClass
}

/**
 * Icon Feat Greater Spell Focus.png Greater Spell Focus - Passive This feat makes it harder for
 * enemies to resist the caster's spells of a particular school by adding +1 to the difficulty class
 * of the spell. This stacks with Spell Focus.
 *
 * Spell Focus for the same school Level 1: Artificer, Bard, Cleric, Druid, Favored Soul Level 1:
 * Sorcerer, Wizard; Level 4: Paladin, Ranger
 */
trait GreaterSpellFocusBase
  extends FeatRequisiteImpl with ClassRequisiteImpl with Passive with RequiresAnyOfClass
  with RequiresAllOfFeat {
  self: GeneralFeat =>

  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.SpellFocus)
}
