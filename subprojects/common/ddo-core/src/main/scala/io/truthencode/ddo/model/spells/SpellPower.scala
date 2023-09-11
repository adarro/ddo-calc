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
package io.truthencode.ddo.model.spells

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.model.effect._
import io.truthencode.ddo.model.skill.Skill
import io.truthencode.ddo.model.skill.Skill.{Heal, Perform, Repair, Spellcraft}
import io.truthencode.ddo.support.SearchPrefix
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}

/**
 * Represents one of the eight schools of magic.
 */
sealed trait SpellPower
  extends EnumEntry with DisplayName with FriendlyDisplay with SkillLinkedPower {

  /**
   * Sets or maps the source text for the DisplayName.
   *
   * @return
   *   Source text.
   */
  override protected def nameSource: String = entryName.splitByCase
}

/**
 * Amplifies or reduces spell damage of a given type
 */
object SpellPower extends Enum[SpellPower] with SearchPrefix {
  override def values: IndexedSeq[SpellPower] = findValues

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified
   * "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "SpellPower"

  case object Acid extends SpellPower with Acid {
    override def linkedSkill: Skill = Spellcraft
  }

  case object Cold extends SpellPower with Cold {
    override def linkedSkill: Skill = Spellcraft
  }

  case object Electric extends SpellPower with Electric {
    override def linkedSkill: Skill = Spellcraft
  }

  case object Fire extends SpellPower with Fire {
    override def linkedSkill: Skill = Spellcraft
  }

  /**
   * Force power also affects your untyped and physical damage spells (such as the bludgeoning
   * portion of ice storm)
   */
  case object Force extends SpellPower with Force {
    override def linkedSkill: Skill = Spellcraft
  }

  /**
   * Light power is also used to determine Alignment based damage
   */
  case object Light extends SpellPower with Light with Alignment {
    override def linkedSkill: Skill = Spellcraft
  }

  /**
   * Negative spellpower is affected by negative enchantments such as Nihil and your Heal skill.
   */
  case object Negative extends SpellPower with Negative {
    override def linkedSkill: Skill = Heal
  }

  /**
   * Poison spell power is affected by negative enchantments such as Nihil and your Spellsight /
   * Spellcraft Skill
   */
  case object Poison extends SpellPower with Poison {
    override def linkedSkill: Skill = Spellcraft
  }

  case object Positive extends SpellPower {
    override def linkedSkill: Skill = Heal
  }

  /**
   * Affects Rust / Repair power. (Repair heals Constructs)
   */
  case object RustRepair extends SpellPower with RustRepair {
    override def linkedSkill: Skill = Repair
  }

  case object Sonic extends SpellPower with Sonic {
    override def linkedSkill: Skill = Perform
  }
}
