/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
package io.truthencode.ddo.model.effect

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.NoDefault
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.attribute.{Attribute => Attributes}
import io.truthencode.ddo.model.feats.{Feat => Feats}
import io.truthencode.ddo.model.skill.{Skill => Skills}

import scala.collection.immutable

trait SearchPattern {
  self: EnumEntry =>
  def searchPattern(target: String = entryName): String = s"$target"
}

/**
  * Denotes which aspect an effect applies to
  */
sealed trait EffectPart extends EnumEntry with SearchPattern

trait SkillEffect extends EffectPart {

  override def searchPattern(target: String = entryName): String =
    s"Skill($target)"

  val skill: Skills
}

trait AttributeEffect extends EffectPart {
  override def searchPattern(target: String): String = s"Attribute($target)"
  val attribute: Attributes
}

trait FeatEffect extends EffectPart {

  override def searchPattern(target: String = entryName): String =
    s"Feat($target)"
  val feat: Feats
}

/**
  * A list of available effect targets.
  * @note This should contain all items viewable from the DDO Character menu
  *       (including the + menu)
  *       Also, should have additional effects such as Caster / Turn Undead Level
  */
object EffectPart extends Enum[EffectPart] with NoDefault[EffectPart] with Searchable[EffectPart] {
  lazy val values = findValues ++ anySkill ++ anyFeat
  // Attribute value affected by Base, Feat, Item, Tomes
  case class Attribute(override val attribute: Attributes) extends AttributeEffect
  private def anyAttribute = Attributes.values.map(Attribute)
  // skill key ability, total mod, rank, ability mod, misc mod
  case class Skill(override val skill: Skills) extends SkillEffect
  private def anySkill = Skills.values.map(Skill)
  case class Feat(override val feat: Feats) extends FeatEffect
  private def anyFeat = Feats.values.map(Feat)
  case object Spell extends EffectPart
  case object Health extends EffectPart
  case object Spellpoints extends EffectPart
  case object DodgeChance extends EffectPart
  /*
  Main
  Feats
  Skills
  Attributes
  Enhancements
  Epic Destinies

  Level
  Heroic
  Epic

  Past Lives

   */

  /* Basics
  Hitpoints (Base, Constitution, Feat)
  Spellpoints (Base, Ability, Feat, Enchanted)
   ki (Base only?)
  BaB

  Movement Speed Multiplier
   */
  /*
   Spell Cost Reduction
   Threat Multiplier
   Spell Pen bonuses
   */
  /*
  General Combat
  Helpless Damage Bonus
  Crit Confirm Bonus
  Crit hit damage bonus
  Fort bypass
  dodge bypass
  sneak attack -
    to hit
    damage
    sneak attack die
   */
  /*
  Melee
  One Handed attack speed bonus
  Two-handed attack speed bonus
  Two weapon attack speed bonus
  quarter staff attack speed bonus
  secondary shield bash bonus
  offhand hit chance
  offhand doublestrike
  strikethrough
    melee threat multiplier


   */
  /*
  Ranged Attack
  bow attack speed
  thrown attack speed
  non-repeating x bow speed
  repeating x bow speed
  ranged threat multiplier

  Effective hit points?
   */
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

}

/**
  *
  * {{{
  * val excCombatMastery5 = Eff(
  *     TriggerEvent.Passive,bonusType = Incite, Magnitude = 5,DifficultyCheck,List(Skill.Trip,Sunder,StunningBlow))
  * //Tendon Slice 10%
  * val tSlice = Eff(OnAttack,Enhancement,10%,???,Hamstring(slow enemy movement))
  * // +6 Enhancement Bonus
  * val eb6 = Eff(Passive,Enhancement,100/6,???Atk / Dmg Bonus)
  * }}}
  * Maiming: This weapon has a twisted haft or grip and spikes
  * along its blade, head, or point. Whenever you score a
  * critical hit with this weapon it deals an amount of
  *  extra untyped damage depending on its
  *  critical multiplier: x2 - 1 to 6, x3 - 2 to 12, x4 - 3 to 18.
  *  {{{
  *  val maiming = Eff(Critical,100,NA,100 / Multiplier(2->1d6,3->2d6,4->3d6),???,Melee/Ranged Attack)
  *  }}}
  */
case class Eff(
  // When this event activates such as passive or some active event
  trigger: TriggerEvent,
  // Incite / Feat / Competence used for stacking rules
  bonusType: BonusType,
  // +5 or 4d3+1 etc
  magnitude: Magnitude,
  // DC difficulty check if applicable
  difficultyCheck: Option[Int],
  // target - skill or attribute or sub effect(s)?
  target: EffectPart
)
