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

import io.truthencode.ddo.activation.OnSpellCastEvent
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Druid
import io.truthencode.ddo.model.misc.DefaultCasterCoolDown
import io.truthencode.ddo.model.spells.{Spell, SpellBookImpl}
import io.truthencode.ddo.support.naming.Prefix
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
 * You gain an additional spell preparation slot per spell level to cast Summon Nature's Ally spell
 * of that level.
 */
protected[feats] trait DruidSpontaneousCasting
  extends FeatRequisiteImpl with ActiveFeat with SpellBookImpl with OnSpellCastEvent
  with GrantsToClass with RequiresAllOfClass with Prefix with DefaultCasterCoolDown {

  /**
   * Delimits the prefix and text.
   */
  override protected val prefixSeparator: String = " "

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = List((Druid, 1))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = List((Druid, 1))

  override def prefix: Option[String] = Some("Druid:")

  abstract override def spellIds: Set[String] =
    super.spellIds ++ Spell.summonNatureAllySpells
}
