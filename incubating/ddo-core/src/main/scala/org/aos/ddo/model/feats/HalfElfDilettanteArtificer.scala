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
package io.truthencode.ddo.model.feats

import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.race.Race
import io.truthencode.ddo.support.requisite.{
  FeatRequisiteImpl,
  RequiresAllOfRace,
  RequiresAttribute
}

/**
  * DilettanteArtificer.bmp
  * Half-Elf Dilettante: Artificer
  * Passive
  * You have watched the artificers of House Cannith work their trade.
  * You gain proficiency with all crossbows, and
  * Artificer Knowledge: Scrolls
  * (You gain a +2 bonus to Use Magical Device checks when using scrolls,
  * and their Caster Level is increased by one - this Caster Level increase is capped by your Intelligence modifier.)
  * You are able to use wands and scrolls as if you are a level one artificer.
  * For item use purposes you count as a level one artificer in addition to any other classes you possess
  * (though this does not grant the ability to use Rune Arms).
  * Half-Elf
  * 13 Intelligence
  */
trait HalfElfDilettanteArtificer
    extends FeatRequisiteImpl
    with HalfElfDilettantePreFix
    with Passive
    with RequiresAttribute
    with RequiresAllOfRace { self: RacialFeat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def requiresAttribute: Seq[(Attribute, Int)] =
    List((Attribute.Intelligence, 13))
}
