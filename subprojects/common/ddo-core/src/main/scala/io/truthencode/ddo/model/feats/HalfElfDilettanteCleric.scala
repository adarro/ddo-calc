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

import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.race.Race
import io.truthencode.ddo.support.requisite.{
  AttributeRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfAttribute,
  RequiresAllOfRace
}

/**
 * DilettanteCleric.bmp Half-Elf Dilettante: Cleric Passive Able to use wands and scrolls as if you
 * were a level one cleric. Half-Elf 13 Wisdom
 */
trait HalfElfDilettanteCleric
  extends FeatRequisiteImpl with HalfElfDilettantePreFix with Passive with AttributeRequisiteImpl
  with RequiresAllOfAttribute with RequiresAllOfRace {
  self: RacialFeat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def allOfAttributes: Seq[(Attribute, Int)] = List((Attribute.Wisdom, 13))
}
