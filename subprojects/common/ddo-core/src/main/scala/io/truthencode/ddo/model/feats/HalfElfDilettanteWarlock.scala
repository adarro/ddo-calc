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

import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.race.Race
import io.truthencode.ddo.support.requisite.{
  FeatRequisiteImpl,
  RequiresAllOfRace,
  RequiresAttribute
}

/**
  * Warlock.png
  * Half-Elf Dilettante: Warlock
  * Passive	Able to use wands and scrolls as if you were a level one warlock.
  * Toggle: You deal 1D4 extra Fire damage with attacks and spells.
  * This trigger at most once every two seconds. This toggle is exclusive with Warlock Pact toggles.
  * Half-Elf
  * 13 Charisma
  */
protected[feats] trait HalfElfDilettanteWarlock
    extends FeatRequisiteImpl
    with HalfElfDilettantePreFix
    with Passive
    with RequiresAttribute
    with RequiresAllOfRace { self: RacialFeat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def requiresAttribute: Seq[(Attribute, Int)] =
    List((Attribute.Charisma, 13))
}
