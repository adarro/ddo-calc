/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: BonusType.scala
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
package io.truthencode.ddo.enhancement

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay, Prefix}
import io.truthencode.ddo.support.slots.{Cosmetic, EquipmentSlot, WearLocation}
import io.truthencode.ddo.{NonStacking, StackingRule, StacksWithAny, StacksWithUnique}

import scala.collection.immutable

/**
 * A Bonus type represents the classifier of a given effect for the purposes of stacking. I.e. Plate
 * armor and a shield each provide bonuses to your Armor Class and have a cumulative benefit.
 * Wearing a shield and casting a 'Shield spell' will not. Only the highest of the two will apply to
 * your total armor class. (Overlapping can sometimes still benefit as the shield spell may provide
 * less armor class than equipping a tower shield, but it also protects against magic missiles.
 */
sealed trait BonusType extends EnumEntry with DisplayName with FriendlyDisplay {

  self: StackingRule =>
  override protected def nameSource: String = entryName.splitByCase
}

/**
 * Flags a bonus to denote that this bonus can generally apply to armor
 */
trait Armor extends BonusType {
  self: StackingRule =>
}

/**
 * Encapsulates the [[https://ddowiki.com/page/Category:Bonus_types]] Several Bonus types are not
 * listed / tagged by the above link. Examples include Armor related ones such as Deflection Bonus
 */
// scalastyle:off number.of.methods
object BonusType extends Enum[BonusType] {
  override def values: immutable.IndexedSeq[BonusType] = findValues ++ mythicSlotAny

  val fnStackNone: PartialFunction[BonusType, BonusType & NonStacking] = { case x: NonStacking =>
    x
  }

  val fnStackAny: PartialFunction[BonusType, BonusType & StacksWithAny] = { case x: StacksWithAny =>
    x
  }

  val fnStackUnique: PartialFunction[BonusType, BonusType & StacksWithUnique] = {
    case x: StacksWithUnique =>
      x
  }

  /**
   * List of bonus types that stack with all but the same type and value. Highest wins.
   */
  lazy val UniqueBonusTypes: Seq[BonusType & StacksWithUnique] = values.collect(fnStackUnique)

  /**
   * List of bonus types that stack with everything. Generally limited to rare 'Misc' bonus and
   * Feats.
   */
  lazy val AnyBonusTypes: Seq[BonusType & StacksWithAny] = values.collect(fnStackAny)

  /**
   * List of bonus types that don't stack.
   */
  lazy val NonStackingBonusTypes: Seq[BonusType & NonStacking] = values.collect(fnStackNone)
  case object ActionBoost extends BonusType with NonStacking

  /**
   * An alchemical bonus is granted by the use of a non-magical, alchemical substance.
   * [[https://ddowiki.com/page/Alchemical_bonus wiki]]
   */
  case object Alchemical extends BonusType with NonStacking

  /**
   * An [[https://ddowiki.com/page/Artifact_bonus Artifact Bonus]] Artifact bonus is a rare bonus
   * type, mostly available granted by item sets. This bonus stacks with all other bonuses.
   */
  case object Artifact extends BonusType with StacksWithAny

  /**
   * An [[https://ddowiki.com/page/Armor_bonus armor]] bonus is a bonus to Armor class from the
   * armor that you are wearing. Armor bonuses do not stack.
   */
  case object Armor extends BonusType with Armor with NonStacking

  /**
   * A [[https://ddowiki.com/page/Circumstance_bonus circumstance bonus]] (or penalty) arises from
   * specific conditional factors impacting the success of the task at hand. Circumstance bonuses
   * stack with all other bonuses, including other circumstance bonuses, unless they arise from
   * essentially the same source.
   */
  case object Circumstance extends BonusType with StacksWithAny

  /**
   * Some feats and enhancements, usually related to some of the fighting styles, grant a
   * [[https://ddowiki.com/page/Combat_Style_bonus Combat Style bonus]]. Multiple bonuses of the
   * same type don't stack.
   *
   * Sources for Combat Style bonuses:
   */
  case object CombatStyle extends BonusType with NonStacking

  /**
   * A [[https://ddowiki.com/page/Competence_bonus competence bonus]] is a type of bonus. It can
   * apply to most anything, but most frequently applies to skills.
   */
  case object Competence extends BonusType with NonStacking

  /**
   * A [[https://ddowiki.com/page/Deflection_bonus deflection bonus]] affects Armor Class and is
   * granted by a spell or magic effect that makes attacks veer off harmlessly. Deflection bonuses
   * stack with all other bonuses to AC except other deflection bonuses.
   */
  case object Deflection extends BonusType with Armor with NonStacking

  /**
   * [[https://ddowiki.com/page/Determination_bonus Determination bonus]] was introduced in Update
   *   26. Multiple determination bonuses don't stack, only the highest applies.
   */
  case object Determination extends BonusType with NonStacking

  /**
   * The [[https://ddowiki.com/page/Dexterity_bonus dexterity bonus]] is a bonus to AC equal to your
   * dexterity modifier. It may be capped by the armor or tower shield your character is wearing, as
   * well by encumbrance (since these impose a maximum dexterity bonus).
   */
  case object Dexterity extends BonusType with Armor with NonStacking

  /**
   * Many of the cleric domain powers triggered by using Turn Undead grant
   * [[https://ddowiki.com/page/Divine_bonus Divine bonus]] to various character statistics
   */
  case object Divine extends BonusType with NonStacking

  /**
   * [[https://ddowiki.com/page/Dodge_bonus Dodge Bonus Mechanic]] This check is done independent of
   * the miss chance resulting from Armor Class and other measures of not getting hit, such as
   * Concealment or Incorporeality. Stacked pre-update 19 and as of update 42 there is a Hard cap of
   * 95%
   */
  case object Dodge extends BonusType with Armor with NonStacking

  /**
   * An [[https://ddowiki.com/page/Enhancement_bonus enhancement bonus]] represents a magical
   * increase in the effectiveness of an item.
   */
  case object Enhancement extends BonusType with NonStacking

  /**
   * [[https://ddowiki.com/page/Epic_bonus Epic bonus]] is a rare bonus type found only on a handful
   * of old epic items (created via Epic Crafting). It gives a stacking bonus to a particular saving
   * throw.
   */
  case object Epic extends BonusType with StacksWithAny

  /**
   * Some magic items grant an equipment bonus to Spell Power, increasing the damage dealt/healed by
   * your spells. Weapons are a common source of
   * [[https://ddowiki.com/page/Equipment_bonus equipment bonus]]
   */
  case object Equipment extends BonusType with NonStacking

  /**
   * Exceptional bonuses are rare bonuses generally only found on high level loot.
   * [[https://ddowiki.com/page/Exceptional_bonus Exceptional bonuses]] stack with all other
   * bonuses, but not with other exceptional bonuses.
   */
  case object Exceptional extends BonusType with NonStacking

  /**
   * A feat bonus is granted by [[https://ddowiki.com/page/Feat_bonus feats]]. Feats can grant
   * bonuses of many kind, from hit points (Toughness) to skills (Skill focus) to armor class
   * (Dodge).
   *
   * Feat bonuses do stack.
   */
  case object Feat extends BonusType with StacksWithAny

  /**
   * When the [[https://ddowiki.com/page/Festive_bonus Festive bonus]] was originally introduced in
   * Update 28 Patch 1 it was known as a Spooky bonus. It was renamed in Update 30 to support being
   * used on non-scary things.
   */
  case object Festive extends BonusType with NonStacking

  /**
   * A [[https://ddowiki.com/page/Guild_bonus Guild bonus]] stacks with other bonus types but not
   * with other Guild bonuses
   */
  case object Guild extends BonusType with NonStacking

  /**
   * Spellcasting Implements, such as Thaumaturgy staffs, grant an
   * [[https://ddowiki.com/page/Implement_bonus implement bonus]] to Spell Power, increasing the
   * damage dealt/healed by spells.
   */
  case object Implement extends BonusType with NonStacking

  /**
   * A bonus to an ability score or skill resulting from powerful magic, such as a wish.
   * [[https://ddowiki.com/page/Inherent_bonus Inherent bonuses]] cannot be dispelled. A character
   * is limited to a total inherent bonus of +8 to any ability score. Multiple inherent bonuses to a
   * particular ability score do not stack, so only the best one applies.
   */
  case object Inherent extends BonusType with NonStacking

  /**
   * An [[https://ddowiki.com/page/Insight_bonus insight bonus]] improves performance of a given
   * activity by granting the character an almost precognitive knowledge of what might occur.
   * Multiple insight bonuses do not stack, only the highest applies.
   */
  case object Insight extends BonusType with NonStacking

  /**
   * Legendary bonus is a new kind of bonus type as of Update 29.
   *
   * Found on Legendary Green Steel equipment set bonus.
   * @note
   *   I am unsure of the stacking specifics and need to verify the stacking
   */
  case object Legendary extends BonusType with NonStacking

  /**
   * A [[https://ddowiki.com/page/Luck_bonus luck bonus]] represents good (or bad) fortune. Multiple
   * luck bonuses on the same character or object do not stack. Only the highest luck bonus applies.
   */
  case object Luck extends BonusType with NonStacking

  /**
   * [[https://ddowiki.com/page/Miscellaneous_bonus Miscellaneous Armor bonuses]] The following
   * miscellaneous bonuses to Armor Class stack with each other in DDO, but miscellaneous bonuses
   * coming from the same source don't stack (ie - 2 paladins' aura).
   */
  case object Miscellaneous extends BonusType with Armor with StacksWithUnique

  /**
   * A [[https://ddowiki.com/page/Morale_bonus morale bonus]] represents the effects of greater
   * hope, courage, and determination (or hopelessness, cowardice, and despair in the case of a
   * morale penalty). Multiple morale bonuses on the same character do not stack. Only the highest
   * morale bonus applies. Non-intelligent creatures (creatures with an Intelligence of 0 or no
   * Intelligence at all) cannot benefit from morale bonuses.
   */
  case object Morale extends BonusType with NonStacking

  /**
   * Some songs, chants, and enhancements available to Bards grant
   * [[https://ddowiki.com/page/Music_bonus Music bonus]].
   */
  case object Music extends BonusType with NonStacking

  /**
   * [[https://ddowiki.com/page/Mythic_bonus Mythic bonus]] was introduced in Update 25. Mythic
   * bonuses stack differently than most bonus types: see quote below. The Mythic bonuses from each
   * slot stack with one another Weapons, belts, gloves, goggles, rings, and trinkets grant Mythic
   * bonus to Melee, Ranged, and Universal Spell Power. Armor, boots, bracers, cloaks, headwear,
   * necklaces, shields grant Mythic bonus to Physical and Magical Resistance Rating. Orbs, rune
   * arms, and collars can appear Shield and/or Weapon boost. (Some) ToEE items can appear with two
   * mythic bonuses, e.g., Weapon and Shield.
   *
   * Magnatude Weapons, armor, shields can have +2 or +4 bonus, +4 is rarer. Clothing and jewelry
   * can have +1 or +3 bonus, +3 is rarer.
   *
   * Unlike most bonus types, all sources of Mythic bonus stack.
   */
  case class Mythic(slot: EquipmentSlot) extends BonusType with StacksWithAny with Prefix {
    override def displaySource: String = slot.displaySource

    /**
     * Optional Prefix, used to separate sub-items such as Spell Critical Schools and also to
     * disambiguate certain entities such as Feat: precision.
     *
     * @return
     *   The optional prefix.
     */
    override def prefix: Option[String] = Some("Mythic")

//      override protected def nameSource: String = slot.displayText
  }

  def mythicSlotAny: Seq[Mythic] = {
    val wls = WearLocation.values
    val wsl = WearLocation.values
      .withFilter(_.isInstanceOf[EquipmentSlot])
      .withFilter(!_.isInstanceOf[Cosmetic])
    for wc <- WearLocation.values yield Mythic(wc.asInstanceOf[EquipmentSlot])
  }

  /**
   * [[https://ddowiki.com/page/Natural_armor_bonus Natural armor]] represents a creature's hide or
   * magical effects that make skin thicker (such as Barkskin). Natural armor provides a bonus to
   * AC. Some natural armor bonuses stack.
   * @todo
   *   Figure out how / what natural armor bonuses stack
   * @note
   *   This should apply to 'plain natural armor' text that states 'Natural Armor' may not actually
   *   be that. i.e. Rough Hide
   */
  case object NaturalArmor extends BonusType with Armor with NonStacking

  /**
   * [[https://ddowiki.com/page/Primal_bonus Primal bonus]] reflects your close ties to the raw
   * nature and to the spirit world. This bonus stacks with all other bonuses.
   */
  case object Primal extends BonusType with Armor with NonStacking

  /**
   * A [[https://ddowiki.com/page/Profane_bonus profane bonus]] (or penalty) stems from the power of
   * evil. Multiple profane bonuses on the same character or object do not stack. Only the highest
   * profane bonus applies.
   */
  case object Profane extends BonusType with NonStacking

  /**
   * [[https://ddowiki.com/page/Psionic_bonus Psionic bonus]] represents the power of mind over
   * matter. In a character's mind, anything can happen
   * @note
   *   This is currently set not to stack, but there is no textual verification on the wiki.
   */
  case object Psionic extends BonusType with NonStacking

  /**
   * A [[https://ddowiki.com/page/Quality_bonus quality bonus]] makes a character better at an
   * action through sheer good workmanship of the item in question. Like most bonus types, multiple
   * quality bonuses do not stack, only the greatest bonus applies.
   */
  case object Quality extends BonusType with NonStacking

  /**
   * [[https://ddowiki.com/page/Rage_bonus Rage Bonus]] Your concentrated fury grants you additional
   * benefits.
   */
  case object Rage extends BonusType with NonStacking

  /**
   * [[https://ddowiki.com/page/Reaper_bonus Reaper Bonus]] Named chest loot from quests run on
   * Reaper difficulty has a chance to gain an additional enchantment
   */
  case object Reaper extends BonusType with StacksWithAny

  /**
   * A [[https://ddowiki.com/page/Resistance_bonus resistance bonus]] gives a bonus to saving
   * throws.
   */
  case object Resistance extends BonusType with NonStacking

  /**
   * [[https://ddowiki.com/page/Sacred_bonus Sacred Bonus]] A bonus that stems from the power of
   * good. Multiple sacred bonuses on the same character or object do not stack. Only the highest
   * sacred bonus applies.
   */
  case object Sacred extends BonusType with NonStacking

  /**
   * A shield bonus is a specific class of bonus to Armor Class normally provided by shields.
   * Multiple shield bonuses do not stack.
   * @note
   *   Two Weapon Defense is not treated as shield bonus in DDO.
   */
  case object Shield extends BonusType with Armor with NonStacking

  /**
   * [[https://ddowiki.com/page/Size_bonus Size bonus]]
   *
   * @note
   *   Defaulting to [[NonStacking]] as wiki does not explicity state
   */
  case object Size extends BonusType with NonStacking

  /**
   * Spooky bonus was renamed [[Festive]] in Update 30
   */
  @deprecated("Was renamed to Festival Bonus in Update 30", "Update 30 Patch")
  case object Spooky extends BonusType with NonStacking

  // Removed Special and Unique until sure they exist In-Game
  /**
   * [[https://ddowiki.com/page/Special_bonus]] stubbed from wiki.
   * @todo
   *   Determine if this is an actual bonus type that exists on an item / feat etc. If not, it may
   *   simply be a trait to denote special handling required.
   */
  // case object Special extends BonusType

  /**
   * Unique is not listed under Bonus types on the wiki
   */
  // case object Unique extends BonusType

  /**
   * Untyped bonuses stack. Note that a bonus without a keyword isn't necessarily an untyped bonus,
   * the description might just be lacking.
   */
  case object Untyped extends BonusType with StacksWithAny

}
