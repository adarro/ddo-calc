/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Category.scala
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
package io.truthencode.ddo.model.stats

import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}
import io.truthencode.ddo.support.StringUtils.Extensions

/**
 * Used to generally classify a stat or effect such as Saving Throws or Movement. These should
 * generally correspond to the Game Client Menu although some are not visible from the UI NOTE: May
 * not exactly match in game UI for UX purposes. (i.e. Dodge chance / AC / Incorporeal should fall
 * under Avoidance Defense, although we are expecting it to appear under main display)
 */
sealed trait Category {
  val catName: String
  val categoryId: String
}

/**
 * Corresponds to effects contributing to your total Miss-chance. These include concealment / dodge
 * / incorporeal etc. https://ddowiki.com/page/Miss_chance
 */
trait MissChance extends Category {
  override val catName: String = "Miss Chance"
  override val categoryId: String = "MissChance"
}

/**
 * Represents a Penalty to target / Enemy Effects like Sunder.
 */
trait MissChancePenalty extends Category {
  override val catName: String = "Miss Chance Penalty"
  override val categoryId: String = "MissChancePenalty"
}

/**
 * Corresponds to effects that affect your chance to hit something.
 */
trait HitChance extends Category {
  override val catName: String = "Hit Chance"
  override val categoryId: String = "HitChance"
}

/**
 * Corresponds to damage reduction from 'Elemental' sources such as Fire. This includes protection /
 * resistance and absorption.
 */
trait ElementalDefenses extends Category {
  override val catName: String = "Elemental Defenses"
  override val categoryId: String = "ElementalDefenses"
}

/**
 * Corresponds to saves vs general / specific spells, traps etc.
 */
trait SavingThrows extends Category {
  override val catName: String = "Saving Throws"
  override val categoryId: String = "SavingThrows"
}

/**
 * Corresponds to your movement speed.
 */
trait Movement extends Category {
  override val catName: String = "Movement"
  override val categoryId: String = "Movement"
}

/**
 * Special Attacks such as Cleave, Trip, Shattermantle or Spring Attack
 */
trait SpecialAttack extends Category {
  override val catName: String = "Special Attack"
  override val categoryId: String = "SpecialAttack"
}

/**
 * A very general category that applies to general and specific spell casting. These should include
 * casting costs, cool-downs, stopping ability (silence / deafness) Spell power MAY fall under this
 * or possibly split into another category as Critical Multiplier / Potently and Universal spell
 * power like effects
 */
trait SpellCasting extends Category {
  override val catName: String = "Spell Casting"
  override val categoryId: String = "SpellCasting"
}

/**
 * General Combat should encompass Tactical feats and others not specific to Melee or Ranged.
 */
trait GeneralCombat extends Category {
  override val catName: String = "General Combat"
  override val categoryId: String = "GeneralCombat"
}

/**
 * Hand to hand specific effects and abilities.
 */
trait MeleeCombat extends Category {
  override val catName: String = "Melee Combat"
  override val categoryId: String = "MeleeCombat"
}

/**
 * Ranged spell and weapon effects.
 */
trait RangedCombat extends Category {
  override val catName: String = "Ranged Combat"
  override val categoryId: String = "RangedCombat"
}

/**
 * Effects that alter the power, range or other undead specific effects. This should also include
 * things that increase the amount of Turns, Cleric levels etc.
 */
trait TurnUndead extends Category {
  override val catName: String = "Turn Undead"
  override val categoryId: String = "TurnUndead"
}

/**
 * General, Misc. or Main stats generally appear on the main character sheet such as Base Attack
 * Bonus
 * @note
 *   BAB may be moved to general combat.
 */
trait General extends Category {
  override val catName: String = "General"
  override val categoryId: String = "General"
}

/**
 * Not super useful by itself, but indicates the effect provides an ability. The power and extent of
 * that ability may depend on other things. Examples may include Bard Songs, Sunder, Attack etc.
 */
trait Ability extends Category {
  override val catName: String = "Ability"
  override val categoryId: String = "Ability"
}

/**
 * Generally your Hit Points
 */
trait Health extends Category {
  override val catName: String = "Health"
  override val categoryId: String = "Health"
}

trait SpellPointPool extends Category {
  override val catName: String = "Spell Point Pool"
  override val categoryId: String = "SpellPointPool"
}

trait Proficiency extends Category {
  override val catName: String = "Proficiency"
  override val categoryId: String = "Proficiency"
}

/**
 * Affects unconscious range, recovery (Die hard)
 */
trait Recovery extends Category {
  override val catName: String = "Recovery"
  override val categoryId: String = "Recovery"
}

// Undead related
