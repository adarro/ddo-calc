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
  object EffectValidation extends LazyLogging {
    lazy val triggerNames: Seq[String] = TriggerEvent.values.map(_.entryName)
    lazy val bonusTypeNames = BonusType.values.map(_.entryName)
    lazy val categories: Seq[String] = EffectCategories.values.map(_.toString).toSeq
    logger.info(s"triggerNames size ${triggerNames.size}")
    def validateName(name: String): Validation[String, String] =
      if name.isEmpty then {
        Validation.fail("Name was empty")
      } else {
        Validation.succeed(name)
      }

    def validateDescription(description: String): Validation[String, String] =
      if description.isEmpty then {
        Validation.fail("Name was empty")
      } else {
        Validation.succeed(description)
      }

    def validateTriggers(triggers: Seq[String]): Validation[String, Seq[String]] =
      if triggers.isEmpty then {
        Validation.fail("At least one valid trigger must be specified")
      } else {
        import io.truthencode.ddo.support.TraverseOps._
        val tIn = triggers.mkString(", ")
        logger.debug(s"Evaluating Triggers $tIn")
        val tCommon = triggers.intersect(triggerNames)
        logger.info(s"intersection found ${tCommon.size} Triggers $tCommon ")
        if tCommon.nonEmpty then {
          // Succeed but warn if there are unknown triggers (which we will filter out)
          val unknowns = triggers.nSelect(triggerNames)
          if unknowns.nonEmpty then {
            val badIds = unknowns.mkString(", ")
            logger.warn(s"Filtering out unknown triggers: $badIds")
          }
          Validation.succeed(tCommon)
        } else { // Not a single valid trigger passed in
          val nSel = triggers.nSelect(triggerNames).mkString(", ")
          Validation.fail(s"Invalid Trigger id's $nSel")
        }
      }

    def validateBonusType(bonusType: String): Validation[String, String] =
      if bonusType.isEmpty then {
        Validation.fail("Bonus Type was empty")
      } else if bonusTypeNames.contains(bonusType) then {
        Validation.succeed(bonusType)
      } else {
        Validation.fail("Unknown or Invalid Bonus Type")
      }

    def validateDetailedEffect(
      id: String,
      description: String,
      triggersOn: Seq[String],
      triggersOff: Seq[String],
      bonusType: String): Validation[String, DetailedEffect] =
      Validation.validateWith(
        validateName(id),
        validateDescription(description),
        validateTriggers(triggersOn),
        validateTriggers(triggersOff),
        validateBonusType(bonusType))(DetailedEffect)
  }

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
