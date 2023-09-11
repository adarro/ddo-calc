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
package io.truthencode.ddo.model.character

import io.truthencode.ddo.model.alignment.Alignments
import io.truthencode.ddo.model.classes.{EpicCharacterClass, HeroicCharacterClass}
import io.truthencode.ddo.model.destiny.EpicDestiny
import io.truthencode.ddo.model.enhancement.Enhancement
import io.truthencode.ddo.model.feats.Feat
import io.truthencode.ddo.model.item.WearableItem
import io.truthencode.ddo.model.race.Race
import io.truthencode.ddo.model.skill.Skill
import io.truthencode.ddo.model.stats._
import io.truthencode.ddo.support.dice.Dice
import io.truthencode.ddo.support.points.{HitPoints, Ki, SpellPoints}

// scalastyle:off number.of.methods
trait Character {

  /**
   * Currently Just an alias for Int but denotes that it is a percentage and may need to be refined
   * to a decimal
   */
  type Pct = Int

  /**
   * Name of character
   */
  val name: String = "Unnamed"

  /**
   * Character Race This should be somewhat stable, however, spells / feats may alter or replace it
   * such as Druid shape shifting, Shrouds, Construct Mastery etc.
   * @note
   *   May need to additionally store an array of applied types such as counting as Undead in
   *   Addition to type.
   */
  val race: SimpleStatItem[Race]

  /**
   * This includes both the Moral and Law axis, i.e. 'Chaotic Good' You may have exactly one
   * alignment.
   */
  val alignment: SimpleStatItem[Alignments]

  // Attributes
  val strength: AttributeStat
  val dexterity: AttributeStat
  val constitution: AttributeStat
  val intelligence: AttributeStat
  val wisdom: AttributeStat
  val charisma: AttributeStat

  /**
   * Holds currently applied enhancements from the skill tree.
   * @note
   *   may need to create a custom type as certain enhancements are multi-tier, i.e. can spend
   *   points for up to three levels
   */
  val enhancements: List[SimpleStatItem[Enhancement]]

  /**
   * Holds current Epic destiny acquisitions
   * @note
   *   will likely need to create custom type
   */
  val epicDestinies: List[SimpleStatItem[EpicDestiny]]
  /* Equipment Slots */
  val equipment: List[WearableItem]

  /**
   * Feats are acquired via leveling and other methods such as favor rewards. They will need to be
   * dynamically derived by a Character Planner.
   * @return
   *   a list of currently acquired Feats
   */
  def feats: List[SimpleStatItem[Feat]]

  /**
   * Skills are given upon character creation, but some such as Perform require trained ranks before
   * they can be used.
   * @return
   *   the list of skills.
   * @note
   *   Skills are dependent on Attributes and should be evaluted after.
   */
  def skills: List[SimpleStatItem[Skill]]

  /**
   * Current Character Level is derived from the amount of heroic, epic and possibly past lives
   * @note
   *   TBD: translating past lives into levels. May simply be a display thing.
   */
  def characterLevel: Int

  /**
   * Represents the current acquired Character classes
   * @return
   *   current heroic classes
   */
  def heroicLevels: List[(Int, HeroicCharacterClass)]

  /**
   * Represents the current Epic Levels
   * @return
   *   current epic levels
   */
  def epicLevels: List[(Int, EpicCharacterClass)]

  /**
   * Holds Past lives from all TR forms (Heroic, Racial, Iconic and Epic) and is used for granting
   * past life feats and / or displaying total character levels.
   * @return
   *   List of acquired past life feats
   */
  def pastLives: List[StatItem[PastLife, Int]]

  /**
   * Current hitpoints based on a calculation of Constitution, Feats, Level etc
   * @return
   */
  def hitPoints: StatItem[HitPoints, Int]

  def spellPoints: StatItem[SpellPoints, Int]

  def ki: StatItem[Ki, Int]

  def bab: StatItem[BaseAttackBonus, Int]

  def msm: StatItem[MovementSpeedModifier, Pct]

  def meleeThreatMultiplier: StatItem[MeleeThreatMultiplier, Pct]

  def rangedThreatMultiplier: StatItem[RangedThreatMultiplier, Pct]

  def spellThreatMultiplier: StatItem[SpellThreatMultiplier, Pct]

  def spellCostReduction: StatItem[SpellCostReduction, Pct]

  def spellPenetrationBonuses: StatItem[SpellPenetrationBonuses, Int]

  //  General Combat
  def helplessDamage: StatItem[HelplessDamage, Pct]

  def criticalHitConfirmation: StatItem[CriticalHitConfirmation, Int]

  def criticalHitDamage: StatItem[CriticalHitDamage, Int]

  def fortificationBypass: StatItem[FortificationBypass, Pct]

  def dodgeBypass: StatItem[DodgeBypass, Pct]

  def sneakAttackDamageBonus: StatItem[SneakAttackDamageBonus, Int]

  def sneakAttackHitBonus: StatItem[SneakAttackHitBonus, Int]
  //    Melee

  def sneakAttackDice: StatItem[SneakAttackHitBonus, Dice]

  def oneHandedAttackSpeedBonus: StatItem[OneHandedAttackSpeedBonus, Pct]

  def twoHandedAttackSpeedBonus: StatItem[TwoHandedAttackSpeedBonus, Pct]

  def twoWeaponAttackSpeedBonus: StatItem[TwoWeaponAttackSpeedBonus, Pct]

  def quarterstaffAttackSpeedBonus: StatItem[QuarterstaffAttackSpeedBonus, Pct]

  def shieldBashChance: StatItem[ShieldBashChance, Pct]

  def secondaryShieldBashChance: StatItem[SecondaryShieldBashChance, Pct]

  def offhandHitChance: StatItem[OffhandHitChance, Pct]

  def offhandDoublestrike: StatItem[OffhandDoublestrike, Pct]

  // def meleeThreatMultiplier: StatItem[MeleeThreatMultiplier,Pct]

  def strikeThroughChance: StatItem[StrikeThroughChance, Pct]

  //    Ranged Attack
  def bowAttackSpeedBonus: StatItem[BowAttackSpeedBonus, Pct]

  def thrownAttackSpeedBonus: StatItem[ThrownAttackSpeedBonus, Pct]

  def nonRepeatingCrossbowAttackSpeedBonus: StatItem[NonRepeatingCrossbowAttackSpeedBonus, Pct]
  // def rangedThreatMultiplier: StatItem[RangedThreatMultiplier,Pct]
  // Effective hit points?

  // Power
  /*
     Spell Power Universal + Force / Cold etc
     Spell Critical Chance Universal + Force / Cold etc
     Spell Critical Multiplier Universal + Force / Cold etc
   */
  /*
    Attack Bonus
    Double-Strike, Off-hand Double-Strike
    Double-Shot
    Melee Speed Bonus
    Ranged Speed Bonus
    Add Strike-through (Replacement for Glancing Blows)

    By-pass
    Deflection Passthrough
     Fortification

     Weapon Power
     Melee
     Ranged

     // vs enemy damage / saves i.e. Favored enemy, Dwarf Giant emnity
   */
  // School / Sphere Evocation / Divine etc
  // Spell Penetration?

  // Defense Protection / Avoidance / Resistance / Absorption
  // Spell Resistance
  /*
     Saves vs
     Spell
     Fear
     Enchantment
     Curses
     Illusion
     Sleep
     Diseases
     Exhaustion
     Paralysis
     Nausea
     Poison

   */

  /*
     Elemental Defenses
     Resistance
     Acid, Cold, Electric, Fire, Light, Negative, Poison, Sonic (Number)

     Absorption
     Acid, Chaos, Cold, Electric, Evil, Fire, Force, Good, Lawful, Light, Negative, Poison, Sonic

     Elemental / Magic etc, Traps, Curse poison
   */
  // Saves: Fort / Reflex / Will
  // Damage Reduction  DR,
  // Resistance (MRR / PRR)

  // Avoidance Concealment, Dodge, Ethereal / Ghostly etc

  /*
     Armor class / active blocking
     Dodge Chance / Max cap
     Missile Defense
     Possibly calculate Base Chance at Level
   */

  def repeatingCrossbowAttackSpeedBonus: StatItem[RepeatingCrossbowAttackSpeedBonus, Pct]
}
