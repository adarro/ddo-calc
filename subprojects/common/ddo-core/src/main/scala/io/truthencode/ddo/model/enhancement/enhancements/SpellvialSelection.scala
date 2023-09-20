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

import io.truthencode.ddo.model.enhancement.enhancements.classbased.BombardierTierOne

trait SpellvialSelection extends BombardierTierOne with ClassEnhancementImpl {

  override lazy val description: Option[String] = Some(
    """Spellvial Selection: Select an Element to specialize in.
      |BombadierVialAcid.png SLA: Vial of Acid
      |BombadierVialCold.png SLA: Vial of Frost
      |BombadierVialElectric.png SLA: Vial of Sparks
      |BombadierVialFire.png SLA: Vial of Flame
      |BombadierVialPoison.png SLA: Vial of Venom
      |Metamagic: Empower, Maximize, Quicken, Heighten, Enlarge, Eschew Materials
      |Spell Point Cost: 4/2/1
      |Cooldown 12/8/4 seconds
      |Primer Element: Crimsonite (Red)
      |Conjuring a bottle of (element), you throw a projectile at a single foe that deals 3 to 8 (element) damage per caster level, max caster level 10.
      |DND Dice: Deals 1d6+2 (element) damage per caster level, max 10d6+20
      |""".stripMargin
  )

  /**
   * Some enhancements can be taken multiple times (generally up to three)
   */
  override val ranks: Int = 3

  override def apCostPerRank: Int = 1

}
