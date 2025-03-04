/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: EffectPart.scala
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

import com.typesafe.scalalogging.LazyLogging
import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.NoDefault
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.abilities.ActiveAbilities
import io.truthencode.ddo.model.attribute.{Attribute => Attributes}
import io.truthencode.ddo.model.feats.{
  Feat => Feats,
  GeneralFeat,
  ParentFeat,
  WeaponProficiencyBase
}
import io.truthencode.ddo.model.skill.{Skill => Skills}
import io.truthencode.ddo.model.stats.{BasicStat, HitChance, MissChance}

trait SearchPattern {
  self: EnumEntry =>
  def searchPattern(target: String = entryName): String
  // = s"$target"
}

/**
 * Denotes which aspect an effect applies to
 */
sealed trait EffectPart extends EnumEntry with SearchPattern

trait SkillEffectPart extends EffectPart with LazyLogging {

  val skill: Skills

  override def searchPattern(target: String = Searchable.stripParentheses(entryName)): String = {
    val sp = io.truthencode.ddo.model.skill.Skill.searchPrefix
    val t = Searchable.stripParentheses(entryName.replace("Skill", ""))
    s"$sp$t".replace("::", ":")
  }
}

trait AbilityEffectPart extends EffectPart with LazyLogging {

  val ability: ActiveAbilities

  override def searchPattern(target: String = Searchable.stripParentheses(entryName)): String = {
    val sp = "Ability:"

    val t = Searchable.stripParentheses(entryName.replace("Ability", ""))
    s"$sp$t".replace("::", ":")
  }
}

trait HitChanceEffectPart extends EffectPart with LazyLogging {

  val basicStat: BasicStat & HitChance

  override def searchPattern(target: String = Searchable.stripParentheses(entryName)): String = {
    val sp = "HitChance:"

    val t = Searchable.stripParentheses(entryName.replace("HitChance", ""))
    s"$sp$t".replace("::", ":")
  }
}

trait MissChanceEffectPart extends EffectPart with LazyLogging {

  val basicStat: BasicStat & MissChance

  override def searchPattern(target: String = Searchable.stripParentheses(entryName)): String = {
    val sp = "MissChance:"

    val t = Searchable.stripParentheses(entryName.replace("MissChance", ""))
    s"$sp$t".replace("::", ":")
  }
}

trait AttributeEffect extends EffectPart {
  val attribute: Attributes

  override def searchPattern(target: String): String = s"Attribute($target)"
}

trait FeatEffect extends EffectPart with LazyLogging {

  val feat: Feats

  override def searchPattern(target: String = entryName): String = {

    val sp = "Feat"

    val t = entryName.replace("Feat:", ":")
    s"$sp$t".replace("::", ":")
  }
}

trait DoubleShotEffect extends EffectPart

trait WeaponProficiencyEffect extends EffectPart {
  val proficiency: WeaponProficiencyBase

  override def searchPattern(target: String): String = s"Proficiency($target)"
}

trait CriticalThreatRangeEffect extends EffectPart {
  override def searchPattern(target: String): String = s"CriticalThreatRange:$target"
}

/**
 * A list of available effect targets.
 * @note
 *   This should contain all items viewable from the DDO Character menu (including the + menu) Also,
 *   should have additional effects such as Caster / Turn Undead Level
 */
object EffectPart extends Enum[EffectPart] with NoDefault[EffectPart] with Searchable[EffectPart] {

  lazy val values: IndexedSeq[EffectPart] =
    findValues ++ anySkill ++ anyFeat ++ anyWeaponProficiency ++ anyAbilities ++ anyMissChance ++ anyHitChance

  private[effect] def anySkill: Seq[Skill] = Skills.values.map(Skill(_))

  def anyAbilities: Seq[ActiveAbility] = ActiveAbilities.values.map(ActiveAbility(_))

  def anyMissChance: Seq[MissChanceEffect] =
    BasicStat.values.collect { case x: MissChance => x }.map(MissChanceEffect(_))

  def anyHitChance: Seq[HitChanceEffect] =
    BasicStat.values.collect { case x: HitChance => x }.map(HitChanceEffect(_))

  private def anyAttribute = Attributes.values.map(Attribute(_))

  private[effect] def anyFeat = Feats.values
    .filterNot(_.isInstanceOf[ParentFeat])
    .map(Feat(_))

  private def anyWeaponProficiency =
    GeneralFeat.ExoticWeaponProficiency.subFeats.map(WeaponProficiency(_))

  // Attribute value affected by Base, Feat, Item, Tomes
  case class Attribute(override val attribute: Attributes) extends AttributeEffect

  // skill key ability, total mod, rank, ability mod, misc mod
  case class Skill(override val skill: Skills) extends SkillEffectPart {
    override def entryName: String =
      s"${io.truthencode.ddo.model.skill.Skill.searchPrefix}$skill"
  }

  case class ActiveAbility(override val ability: ActiveAbilities) extends AbilityEffectPart {
    override def entryName: String =
      s"${ActiveAbilities.searchPrefix}$ability"
  }

  // Miss Chance
  case class MissChanceEffect(override val basicStat: BasicStat & MissChance)
    extends MissChanceEffectPart {
    override def entryName: String = s"MissChance:${basicStat.entryName}"
  }

  case class HitChanceEffect(override val basicStat: BasicStat & HitChance)
    extends HitChanceEffectPart {
    override def entryName: String = s"HitChance:${basicStat.entryName}"
  }

  // Feats
  case class Feat(override val feat: Feats) extends FeatEffect {
    override def entryName: String = s"Feat:${feat.entryName}"
  }

  case class WeaponProficiency(override val proficiency: WeaponProficiencyBase)
    extends WeaponProficiencyEffect

  case object Spell extends EffectPart {
    override def searchPattern(target: String): String = s"Spell:$target"
  }

  case object DoubleShot extends EffectPart {
    override def searchPattern(target: String): String = s"DoubleShot"
  }

  case object Health extends EffectPart {
    override def searchPattern(target: String): String = s"Health:$target"
  }

  case object Spellpoints extends EffectPart {
    override def searchPattern(target: String): String = s"SpellPoints:$target"
  }

  case object CriticalThreatRange extends EffectPart {
    override def searchPattern(target: String): String = s"CriticalThreatRange:$target"
  }

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
 * {{{
 * val excCombatMastery5 = Eff(
 *     TriggerEvent.Passive,bonusType = Incite, Magnitude = 5,DifficultyCheck,List(Skill.Trip,Sunder,StunningBlow))
 * //Tendon Slice 10%
 * val tSlice = Eff(OnAttack,Enhancement,10%,???,Hamstring(slow enemy movement))
 * // +6 Enhancement Bonus
 * val eb6 = Eff(Passive,Enhancement,100/6,???Atk / Dmg Bonus)
 * }}}
 * Maiming: This weapon has a twisted haft or grip and spikes along its blade, head, or point.
 * Whenever you score a critical hit with this weapon it deals an amount of extra untyped damage
 * depending on its critical multiplier: x2 - 1 to 6, x3 - 2 to 12, x4 - 3 to 18.
 * {{{
 *   val maiming = Eff(Critical,100,NA,100 / Multiplier(2->1d6,3->2d6,4->3d6),???,Melee/Ranged Attack)
 * }}}
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
