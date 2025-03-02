/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: package.scala
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
package io.truthencode.ddo.model

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.api.model.effect.DetailedEffect
import io.truthencode.ddo.enhancement.BonusType
import zio.prelude.Validation

/**
 * Common constants and functions for generating, reading, and evaluating effects.
 */
package object effect {
  // scalastyle:off
  object EffectValidation extends LazyLogging {}

  object EffectCategories extends Enumeration {
    type EffectCategory = Value
    val MissChance,

    /**
     * Represents a Penalty to target / Enemy Effects like Sunder.
     */
    MissChancePenalty,

    /**
     * Corresponds to effects that affect your chance to hit something.
     */
    HitChance,

    /**
     * Corresponds to damage reduction from 'Elemental' sources such as Fire. This includes
     * protection / resistance and absorption.
     */
    ElementalDefenses,

    /**
     * Corresponds to saves vs general / specific spells, traps etc.
     */
    SavingThrows,

    /**
     * Corresponds to your movement speed.
     */
    Movement,

    /**
     * Special Attacks such as Cleave, Trip, Shattermantle or Spring Attack
     */
    SpecialAttack,

    /**
     * A very general category that applies to general and specific spell casting. These should
     * include casting costs, cool-downs, stopping ability (silence / deafness) Spell power MAY fall
     * under this or possibly split into another category as Critical Multiplier / Potentcy and
     * Universal spell power like effects
     */
    SpellCasting,

    /**
     * General Combat should encompass Tactical feats and others not specific to Melee or Ranged.
     */
    GeneralCombat,

    /**
     * Hand to hand specific effects and abilities.
     */
    MeleeCombat,

    /**
     * Ranged spell and weapon effects.
     */
    RangedCombat,

    /**
     * Effects that alter the power, range or other undead specific effects. This should also
     * include things that increase the amount of Turns.
     */
    TurnUndead,

    /**
     * General, Misc. or Main stats generally appear on the main character sheet such as Base Attack
     * Bonus
     *
     * @note
     *   BAB may be moved to general combat.
     */
    General,

    /**
     * Your ability stat (Strength, Charisma etc) Not super useful by itself, but indicates the
     * effect provides an ability. The power and extent of that ability may depend on other things.
     * Examples may include Bard Songs, Sunder, Attack etc.
     */
    Ability,

    /**
     * Skills such as Perform or Hide.
     */
    Skill,

    /**
     * Generally your Hit Points
     */
    Health, SpellPointPool, Proficiency,

    /**
     * Affects unconscious range, recovery (Die hard)
     */
    Recovery,

    /**
     * Stance that can be toggled on / off to enable certain benefits
     */
    Stance = Value

  }
}
