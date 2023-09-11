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
package io.truthencode.ddo.model.enhancement.enhancements

import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Alchemist
import io.truthencode.ddo.model.enhancement.enhancements.classbased.BombardierCore
import io.truthencode.ddo.support.points.SpendablePoints
import io.truthencode.ddo.support.tree.TreeLike

trait Multivial extends BombardierCore with ClassEnhancementImpl {

  override lazy val description: Option[String] = Some(
    """Select an Element to specialize in.
      |BombadierMultivialAcid.png SLA: Multivial of Acid(SP:15, Cooldown:12 seconds)
      |BombadierMultivialCold.png SLA: Multivial of Frost(SP:15, Cooldown:12 seconds)
      |BombadierMultivialElectric.png SLA: Multivial of Sparks(SP:15, Cooldown:12 seconds)
      |BombadierMultivialFire.png SLA: Multivial of Flame(SP:15, Cooldown:12 seconds)
      |BombadierMultivialPoison.png SLA: Multivial of Poison(SP:15, Cooldown:12 seconds)
      |Primer Element: Crimsonite (Red),
      |Passive: +4 Intelligence, and 2 extra Burning Ambition Dice.
      |While in Pyrite Reaction, you gain +2 Conjuration DCs.
      |""".stripMargin
  )

  /**
   * Some enhancements can be taken multiple times (generally up to three)
   */
  override val ranks: Int = 1

  override def apCostPerRank: Int = 1

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = Seq((Alchemist, 20))

  override def progressionInTree: Seq[(TreeLike, SpendablePoints, Int)] = Seq((tree, 40))
}
