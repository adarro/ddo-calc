/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
package org.aos.ddo.model.classes

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.support.SearchPrefix

import scala.collection.immutable

/** Represents one of the playable character classes.
  */
sealed trait HeroicCharacterClass extends EnumEntry

object HeroicCharacterClass extends Enum[HeroicCharacterClass] with SearchPrefix {

  /**
    *
    * The [http://ddowiki.com/page/Artificer Artificer] combines magic with weapon technology and skill.
    * Traditionally, Artificers prefer to avoid getting their hands dirty in a fight, using personally-made
    *     constructs that perform a variety of tasks but are especially capable in combat.
    *     Artificers are a jack-of-all-trades class: they can serve as excellent healers, decent offensive spellcaster, ranged combatants, and many more roles.
    *     Artificers gain Intelligence enhancements as they level up.
    *
    * The Artificer class must either be purchased in the DDO Store for DDO Points or unlocked on a per-server basis
    *     by earning 150 House Cannith favor on a single character.
    */
  case object Artificer extends HeroicCharacterClass

  /**
    * A Barbarian is a warrior who has special powers when enraged and specializes in dealing heavy damage.
    * Barbarians wear less armor than fighters, but have more HP and some innate damage reduction.
    * Uncanny Dodge allows them to avoid sneak attacks and have excellent reflex saves in limited bursts.
    * While many Barbarian abilities are geared towards melee combat, they can also make great ranged combatants.
    * Barbarians gain Constitution enhancements as they level up.
    */
  case object Barbarian extends HeroicCharacterClass

  /**
    * Bards possess many skills with some special spell casting ability.
    * Their spells consist of a mix of both divine and arcane, but are considered arcane overall.
    * Bards are specialists in songs that buff a party; they can use arcane spells focused on crowd control, healing, and buffs.
    * Bards are also very good at learning the Use Magic Device skill to operate any kind of magical item. Bards gain Charisma enhancements as they level up.
    * Bards have been called the "best sixth man", because using a Bard to fill the final slot in a party means that their powerful musical buffs
    *     boost five other players, and their respectable healing abilities assist the main healer in keeping the party alive.
    */
  case object Bard extends HeroicCharacterClass

  /**
    * Clerics are divine spell casters who specialize in healing and defense spells as well as some offensive ability.
    * Their single current Prestige Enhancement line allows them a unique type of free healing.
    * Most people think of clerics when asking for a healer for their group.
    *
    * Clerics use Wisdom for both spell points and spell casting effectiveness (DCs), and may gain Wisdom and Charisma enhancements as they level up.
    * They can use any armor without penalty, but they are only proficient with Simple Weapons.
    */
  case object Cleric extends HeroicCharacterClass

  /**
    * Take the Druidic oath and get closer to nature.
    * Shed your heavy metal armor in exchange for resistance to the harsh aspects of nature like entanglement, poison, and others.
    * Druids have powerful divine spell casting abilities and can transform into savage animals and elementals, unleashing primal forces upon their foes.
    * Druids use Wisdom when determining their spell points and spell casting effectiveness.
    * They can also be excellent melee combatants if strength and constitution are not ignored.
    *
    * Melee, healing, and nuking are all options for roles in low to mid level groups.
    * Druids make strong solo characters considering their melee and self-healing options, especially at low and mid levels.
    *
    * The Druid class is free for VIP accounts, but others must purchase the class in the DDO store for Turbine Points.
    */
  case object Druid extends HeroicCharacterClass

  /**
    * A divine caster that follows the path of the Cleric but uses divine magic to destroy as well as heal,
    *     Favored Souls have fewer spells than Clerics, but can cast more often.
    *     heir single current Prestige Enhancement line increases their offensive effectiveness, or that of a single ally.
    *     Other enhancements include certain stacking energy resistance and inherent Damage Reduction, making them more than just clerics with
    *     "fewer spells, but more casting".
    *     Favored Souls use Wisdom for spell casting effectiveness (DCs), but Charisma for spell points,
    *     and may gain Wisdom or Charisma enhancements as they level up.
    *     They can use Light or Medium armor, and have limited martial weapon proficiency (only in the chosen weapon of their god).
    *
    * The Favored Soul class must be purchased in the DDO store for Turbine Points or
    *     unlocked on a per server basis by earning 2500 Total FavorPatron on a single character.
    */
  case object FavoredSoul extends HeroicCharacterClass

  /**
    * Fighters are warriors with extra feats, allowing them to specialize in combat.
    * There are many ways to fight: two-handed, dual-wielding or even with a bow... you name it, a fighter can specialize in it.
    * As such, players should examine thoroughly what feats they would like to pick before they create this versatile front-line class.
    * For example, if you specialize in dual weapons, you'll need good reflexes (i.e., high Dexterity).
    * If specialization in two-handed weapons is your goal, Strength is the most important stat.
    * Some tactical feats also require an above average Intelligence.
    * Fighters gain Strength enhancements as they level up.
    */
  case object Fighter extends HeroicCharacterClass

  /**
    * A Monk is a combatant without shields and armor, using simple or no weapons,
    *     that performs amazing techniques in battle by using a power source called ki.
    *     Through intense physical training and mental discipline, Monks gain the ability to generate and control ki.
    *
    * In order to use their special abilities, monks must be Centered in a state of physical and mental balance.
    * To remain centered, a monk must be unencumbered.
    * Your overall encumbrance is based on your Strength and on the total weight of all items in your inventory.
    * Monks cannot use a shield, must wear robes or outfits and must fight unarmed or with special
    *     ki weapons: quarterstaffs, handwraps, kamas and shurikens unless they master in specialized training through feats or
    *     other class enhancements to enable other weapons as Centered.
    *
    * The Monk class is free for VIP accounts, but others must purchase the class in the DDO store for Turbine Points.
    */
  case object Monk extends HeroicCharacterClass

  /**
    * A Paladin is a warrior that trades some melee power
    * for the ability to cast divine spells
    * .Paladins also have a higher ability to avoid getting hit
    * , and can self - heal in limited bursts better than most.The Paladin often has the best saving throws of any character
    * , gains immunity to fear and disease
    * , and may also serve as backup healers
    * for short encounters
    * .They also have passive auras that aid their party members when facing evil creatures
    * .Paladins gain Charisma enhancements as they level up
    * .
    */
  case object Paladin extends HeroicCharacterClass

  /**
    * Rangers are inherently both archers and a dual -wielding melee  class;
    * in exchange
    * for removing animal companions
    * , DDO gives all rangers both combat styles.MustContainAtLeastOne of the most skilled hunters
    * , the Ranger is among the best in stealth and can cast divine spells.Rangers increase their damage
    * with Favored Enemy feats
    * , where they can pick up to five kinds of monsters to specialize in fighting
    * .Rangers may detect secret doors and traps(but not disable them).Rangers gain Dexterity enhancements as they level up
    */
  case object Ranger extends HeroicCharacterClass

  /**
    * Rogues get the most skills in the game
    * .While finding and disarming traps is a key skill
    * , the Rogue can also deal devastating sneak attack damage to serve as a great melee combatant
    * .Rogues are adept at avoiding aggro and being stealthy as they are not front -line fighters
    * .Rogues can also open locks and find hidden doors
    * , making them a welcome addition to any party
    * .Rogues gain Dexterity enhancements as they level up
    */
  case object Rogue extends HeroicCharacterClass

  /**
    * A Sorcerer is a focused caster
    * .Sorcerers know only a small subset of all available arcane spells
    * , but they cast that subset faster and more often
    * , and have more spell points compared to wizards
    * .Sorcerers gain Charisma enhancements as they level up
    * , allowing the casting of more and stronger spells
    * .
    */
  case object Sorcerer extends HeroicCharacterClass

  /**
    * Warlocks are Eldritch casters who form pacts
    * with powerful beings
    * , and seek out rare and often forbidden knowledge
    * .They deal large amounts of damage
    * with their Eldritch Blast
    * , and back that damage up
    * with a variety of bluff
    * , control
    * , and utility abilities.
    */
  case object Warlock extends HeroicCharacterClass

  /**
    * A Wizard is an adaptive caster
    * .Wizards are able to switch spells after resting or
    * while in taverns
    * .Wizards have more spells on tap than a Sorcerer
    * , but are limited in the speed and frequency of their casting.Wizards focus on their Intelligence ability
    * for stronger or more numerous spells available
    */
  case object Wizard extends HeroicCharacterClass

  override def values: immutable.IndexedSeq[HeroicCharacterClass] = findValues

  /**
    * Used when qualifying a search with a prefix.
    * Examples include finding "HalfElf" from qualified "Race:HalfElf"
    *
    * @return A default or applied prefix
    */
  override def searchPrefixSource: String = "CharacterClass"
}
