/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Skill.scala
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
package io.truthencode.ddo.model.skill

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.activation.{
  ActivationType,
  ActivationTypeImpl,
  AtWillEvent,
  PassiveActivation,
  TriggeredActivationImpl
}
import io.truthencode.ddo.model.attribute.*
import io.truthencode.ddo.support.SearchPrefix
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}

import scala.collection.immutable.IndexedSeq

sealed trait Skill
  extends EnumEntry with DisplayName with FriendlyDisplay with LinkedAttributeImpl
  with UsingSkillSearchPrefix {
  self: ActivationType =>

  /**
   * Sets or maps the source text for the DisplayName.
   *
   * @return
   *   Source text.
   */
  override protected def nameSource: String = entryName
}

object Skill extends Enum[Skill] with SearchPrefix {

  override def values: IndexedSeq[Skill] = findValues

  override def searchDelimiter: Option[String] = Some(":")

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified
   * "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "Skill"

  /**
   * Increases the speed with which you get up after being knocked down.
   */
  case object Balance
    extends Skill with TriggeredActivationImpl with PassiveActivation with DexterityLinked

  /**
   * Allows you to bluff certain NPCs, draw monsters away from their group, or make an individual
   * monster vulnerable to sneak attack for six seconds. You also gain a decrease in threat
   * generation for a short period of time after successfully using this ability. (15 second
   * cooldown, Shares cooldown with Diplomacy)
   */
  case object Bluff extends Skill with TriggeredActivationImpl with AtWillEvent with CharismaLinked

  /**
   * Allows you a chance to continue casting a spell after being damaged or otherwise interrupted.
   * Allows monks to retain Ki.
   */
  case object Concentration
    extends Skill with ActivationTypeImpl with PassiveActivation with ConstitutionLinked

  /**
   * Allows you to negotiate more effectively with certain NPCs and to encourage monsters to find
   * targets other than yourself. Any accumulated threat you have with enemies that you successfully
   * diplomacize is reduced. (15 second cooldown, Shares cooldown with Bluff)
   */
  case object Diplomacy
    extends Skill with TriggeredActivationImpl with AtWillEvent with CharismaLinked

  /**
   * Can be used to disarm traps in dungeons. Requires that the control box of the trap has been
   * located by a successful Search check. If the Disable Device check succeeds, you disable the
   * trap. If it fails by 4 or less, you have failed but can try again. If you fail by 5 or more the
   * control box explodes (causing some damage to those in an area around the box) and can't be used
   * again (the trap will remain dangerous, of course!). Using this skill consumes one thieves'
   * tool.
   */
  case object DisableDevice
    extends Skill with TriggeredActivationImpl with AtWillEvent with IntelligenceLinked

  /**
   * Allows you to negotiate better prices with vendors.
   */
  case object Haggle
    extends Skill with ActivationTypeImpl with PassiveActivation with CharismaLinked

  /**
   * Allows you to revive unconscious and bleeding companions to 1 hit point (each attempt requires
   * one healing kit). Also, the character with the highest Heal skill is used when using Rest
   * shrines to dictate how much health is recovered. Does not affect Warforged characters. Each
   * point of skill adds 1 point to Positive and Negative Spell Power. (1 second cooldown)
   */
  case object Heal extends Skill with ActivationTypeImpl with PassiveActivation with WisdomLinked

  /**
   * Allows you to sneak past monsters, avoiding their sight. Hide ties directly into the feat
   * Sneak.
   */
  case object Hide extends Skill with ActivationTypeImpl with PassiveActivation with DexterityLinked

  /**
   * Allows you to intimidate certain NPCs and to draw the attention of monsters while in battle.
   * You will suffer a -4 penalty to your intimidate skill for every size category you are smaller
   * than the creature you are trying to intimidate, or +4 for every size category you are larger.
   * Halflings suffer a -4 penalty to Intimidate due to their small size. (15 second cooldown)
   */
  case object Intimidate
    extends Skill with TriggeredActivationImpl with AtWillEvent with CharismaLinked

  /**
   * Allows you to jump higher. This skill has a maximum cap of 40. 9th level and higher casters can
   * cast +30 Jump (spell)
   */
  case object Jump extends Skill with ActivationTypeImpl with PassiveActivation with StrengthLinked

  /**
   * Allows you to hear approaching enemies. You will be notified by red pulses (footsteps) as they
   * approach. There are certain quests that utilize the listen check for floor-crumbling traps
   * (i.e. The Chamber of Raiyum).
   */
  case object Listen extends Skill with ActivationTypeImpl with PassiveActivation with WisdomLinked

  /**
   * Allows you to sneak past monsters, avoiding their hearing. It ties directly into the feat
   * Sneak.
   */
  case object OpenLock
    extends Skill with TriggeredActivationImpl with AtWillEvent with DexterityLinked

  /**
   * Allows you to sneak past monsters, avoiding their hearing. It ties directly into the feat
   * Sneak.
   */
  case object MoveSilently
    extends Skill with ActivationTypeImpl with PassiveActivation with DexterityLinked

  /**
   * Allows you to use and improve your musical ability to inspire and fascinate others. This skill
   * is required for bards to be able to use their Bardic Music. Each point of skill adds 1 point to
   * Sonic Spell Power.
   */
  case object Perform
    extends Skill with ActivationTypeImpl with PassiveActivation with CharismaLinked

  /**
   * Allows you to restore a disabled warforged character to 1 hit point (each attempt requires one
   * repair kit). Also, the character with the highest Repair skill is used when using Rest shrines
   * to dictate how much health is recovered. Only affects Warforged characters. Each point of skill
   * adds 1 point to Repair and Rust Spell Power. (1 second cooldown)
   */
  case object Repair
    extends Skill with TriggeredActivationImpl with PassiveActivation with AtWillEvent
    with IntelligenceLinked

  /**
   * Allows you to find hidden doors, traps, and objects when activated. Note that (like Spot) there
   * is no die roll involved. If your score is high enough you will always succeed and if it's too
   * low you'll never find something. (1 second cooldown)
   */
  case object Search
    extends Skill with TriggeredActivationImpl with AtWillEvent with IntelligenceLinked

  /**
   * Adds 1 point of Spell Power per point of skill with following damage types: Acid, Cold,
   * Electric, Fire, Force and Light.
   */
  case object Spellcraft
    extends Skill with ActivationTypeImpl with PassiveActivation with IntelligenceLinked

  /**
   * Allows you to sense nearby hidden doors, traps, objects, and stealthed enemies. Except for the
   * enemies, the exact locations will be unknown until a search check is successfully made.
   */
  case object Spot extends Skill with ActivationTypeImpl with PassiveActivation with WisdomLinked

  /**
   * Allows you to swim faster and for a longer period of time underwater.
   */
  case object Swim extends Skill with ActivationTypeImpl with PassiveActivation with StrengthLinked

  /**
   * Tumbling Allows you to tumble and roll away from attacking enemies. Tumbling can be
   * accomplished by blocking and using a movement key. A skill rating higher than 30 allows you to
   * dive to the side and do backflips instead of rolling. Skill also reduces falling damage.
   */
  case object Tumble
    extends Skill with ActivationTypeImpl with PassiveActivation with DexterityLinked

  /**
   * Grants you the ability to use magic devices which you could not normally use. This also allows
   * you to bypass racial and alignment restrictions upon items.
   */
  case object UseMagicDevice
    extends Skill with ActivationTypeImpl with PassiveActivation with CharismaLinked
}
