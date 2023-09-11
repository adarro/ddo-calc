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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Alchemist
import io.truthencode.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
 * Alchemists hava a unique way of combining their spells as they cast them. As an Alchemist, your
 * spells are each associated with a different Primer: Crimsonite, Gildleaf, or Ceruleite. Casting a
 * spell from a single Primer and then casting a spell from a different Primer will activate a
 * Reaction depending on the Primer of the spells cast. Reactions come in three variants: Pyrite,
 * Verudite or Orchidium.
 * @see
 *   [[https://ddowiki.com/page/Alchemical_Spellcasting]]
 */
protected[feats] trait AlchemicalSpellcasting
  extends FeatRequisiteImpl with ClassRequisiteImpl with GrantsToClass with RequiresAllOfClass
  with Passive {
  private[this] val cls = (Alchemist, 1)
  abstract override def grantToClass: Seq[(HeroicCharacterClass, Int)] = super.grantToClass :+ cls

  abstract override def allOfClass: Seq[(HeroicCharacterClass, Int)] = super.allOfClass :+ cls
}
