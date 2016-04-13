/** Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *        http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package org.aos.ddo

import enumeratum.{ EnumEntry, Enum ⇒ SmartEnum }

/** Base trait used to enumerate qualifications.
  */
sealed trait Requirement extends EnumEntry

/** Used to enumerate prerequisites or qualifications for a given feat, spell, enhancement,
  * equipping an item etc.
  */
object Requirement extends SmartEnum[Requirement] {
  /** @param id the required race
    * @param level the minimum character level.
    *
    * @example
    * ReqRace("Drow",6) would represent a level 6 or greater player of the drow race.
    */
  case class ReqRace(id: String, level: Int) extends Requirement
  /** Represents Total Action points spent
    * @param amount The amount of points that must be spent to qualify for.
    * Generally used as a requisite for enhancement trees
    */
  case class ReqActionPoint(amount: Int) extends Requirement
  /** Represents the Total points already spent in a given tree to qualify.
    * @param id the Enhancement Tree
    * @param amount the total amount of points spent within the given tree.
    */
  case class ReqActionPointsInTree(id: String, amount: Int) extends Requirement
  /** Represents the minimum level of a given class to qualify
    * @param id the id of the player class
    * @param level the minimum level for a class.
    */
  case class ReqClass(id: String, level: Int) extends Requirement
  /** Represents a prerequisite enhancement, generally used as a
    * prerequisite for a higher level enhancement.
    *
    * @param id the id of the enhancement
    */
  case class ReqEnhancement(id: String) extends Requirement
  /** Represents a particular alignment, i.e. A Pure Good weapon requires [[MoralAxis.Good]] (Either the law or moral axis)
    * Some items, such as Generated armors such as Armors of Stability give bonus if the character is [[org.aos.ddo.Alignments.TrueNeutral]].
    * @param id either a fully qualified Alignment (i.e. ChaoticGood) or one of the axis, i.e. Lawful or Evil
    *
    */
  case class ReqAlignment(id: Either[AlignmentType, Alignments]) extends Requirement
  /** Represents the minimum Guild level a player must be to equip an item.
    * @param id The level of the Guild the player must be a member of
    */
  case class ReqGuildLevel(id: Int) extends Requirement
  /** Represents the minimum level of the skill to activate an ability or
    * qualify for an enhancement.
    * @param id Identifies the skill, i.e. 'Bluff'
    * @param amount the minimum trained or effective level of the skill.
    * @param trained determines if the skill level.
    * true (i.e. the base skill must be explicitly trained to this level.
    * false the total effective skill must be this level.
    * i.e. can include bonuses from items / ability scores etc.
    */
  case class ReqSkill(id: Int, amount: Int, trained: Boolean = false) extends Requirement
  /** Represents the minimum total character level required to activate or select
    * a skill, wear an item etc.
    * @param level Minimum character level.
    */
  case class ReqCharacterLevel(level: Int) extends Requirement
  val values = findValues
}
