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
package io.truthencode.ddo.support.requisite

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.Abbreviation
import io.truthencode.ddo.model.alignment.{AlignmentType, Alignments, LawAxis, MoralAxis}
import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.favor.FavorPatron
import io.truthencode.ddo.model.feats.Feat
import io.truthencode.ddo.model.race.Race
import io.truthencode.ddo.model.skill.Skill
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, Prefix}
import io.truthencode.ddo.support.points.{Progression, SpendablePoints}
import io.truthencode.ddo.support.tree.TreeLike

import scala.collection.immutable.IndexedSeq

/**
 * Base trait used to enumerate qualifications.
 */
sealed trait Requirement
  extends EnumEntry with DisplayName with Prefix with DefaultRequirementSort {
  override def entryName: String = displaySource
}

/**
 * Used to enumerate prerequisites or qualifications for a given feat, spell, enhancement, equipping
 * an item etc.
 */
object Requirement extends Enum[Requirement] {
  case class GroupedRequirement[T <: Requirement](t: T, key: String, reqType: RequisiteType)
    extends Requirement {
    override def alphaSortKey: String = key

    /**
     * Sets or maps the source text for the DisplayName.
     *
     * @return
     *   Source text.
     */
    override protected def nameSource: String = t.displaySource

    /**
     * Optional Prefix, used to separate sub-items such as Spell Critical Schools and also to
     * disambiguate certain entities such as Feat: precision.
     *
     * @return
     *   The optional prefix.
     */
    override def prefix: Option[String] = t.prefix
  }
  def values: IndexedSeq[Requirement] =
    findValues ++ races ++ classes ++ alignments ++ skills

  private def races = Race.values.map { x =>
    ReqRace(x.entryName, 0)
  }

  private def classes = HeroicCharacterClass.values.map { x =>
    ReqClass(x.entryName, 0)
  }

  private def alignments =
    Alignments.values.map { x =>
      ReqAlignment(Right(x))
    } ++
      LawAxis.values.map { x =>
        ReqAlignment(Left(x))
      } ++
      MoralAxis.values.map { x =>
        ReqAlignment(Left(x))
      }

  private def skills = Skill.values.map { x =>
    ReqSkill(id = x.entryName, amount = 0)
  }

  /**
   * @param id
   *   the required race
   * @param level
   *   the minimum character level.
   * @example
   *   ReqRace("Drow",6) would represent a level 6 or greater player of the Drow race.
   */
  case class ReqRace(id: String, level: Int) extends Requirement with NumberRequirementSort {
    override def prefix: Option[String] = Some(Race.searchPrefixSource)

    /**
     * @inheritdoc
     */
    override protected def nameSource: String = id.splitByCase.toPascalCase

    override def numericalSortKey: Int = level

    override def alphaSortKey: String = id
  }

  /**
   * Represents Total Action points spent
   *
   * @param tree
   *   The type discriminator used to determine which tree (Epic Destiny line vs Ranger enhancement
   *   tree) which includes point type (EDP vs Action Points vs Survival Points etc)
   * @param amount
   *   The Total amount of points that must be spent to qualify for. Generally used as a requisite
   *   for enhancement trees
   * @note
   *   Fate Points / Karma may need to be handled separately
   */
  case class ReqPointsSpentInTree(tree: TreeLike, amount: Int)
    extends Requirement with Progression {
    override val points: SpendablePoints = tree.pointType

    override def prefix: Option[String] = Some(tree.entryName)

    /**
     * @inheritdoc
     */
    override protected def nameSource: String =
      tree.entryName.splitByCase.toPascalCase

    override def alphaSortKey: String = tree.pointType.displayText
  }

  /**
   * Represents Total Action points spent
   *
   * @param points
   *   The type discriminator used to determine Epic Destiny vs Action Points etc
   * @param amount
   *   The Total amount of points that must be spent to qualify for. Generally used as a requisite
   *   for enhancement trees
   */
  case class ReqPointsSpent(points: SpendablePoints, amount: Int) extends Requirement {
    override def prefix: Option[String] = Some(points.entryName)

    override def alphaSortKey: String = nameSource

    /**
     * Sets or maps the source text for the DisplayName.
     *
     * @return
     *   Source text.
     */
    override protected def nameSource: String = points.entryName
  }

  /**
   * Represents the Total points already spent in a given tree to qualify.
   *
   * @param id
   *   the Enhancement Tree
   * @param amount
   *   the total amount of points spent within the given tree.
   */
  case class ReqActionPointsInTree(id: String, amount: Int) extends Requirement {
    override def prefix: Option[String] = Some("ActionPointsSpentInTree")

    override def alphaSortKey: String = nameSource

    /**
     * @inheritdoc
     */
    override protected def nameSource: String = id.splitByCase.toPascalCase
  }

  case class ReqBaseAttackBonus(amount: Int)
    extends Requirement with Abbreviation with NumberRequirementSort {

    override def alphaSortKey: String = abbr

    override def numericalSortKey: Int = amount

    /**
     * The short form of the word
     */
    override val abbr: String = "BaB"

    override def prefix: Option[String] = None

    /**
     * Expands the abbr to its full value
     */
    override def toFullWord: String = entryName.splitByCase

    /**
     * @inheritdoc
     */
    override protected def nameSource: String = abbr.splitByCase.toPascalCase

    override def displayText: String = s"Base Attack Bonus: $amount"
  }

  /**
   * Represents the minimum level of a given class to qualify
   *
   * @param id
   *   the id of the player class
   * @param level
   *   the minimum level for a class.
   */
  case class ReqClass(id: String, level: Int) extends Requirement with NumberRequirementSort {
    override def prefix: Option[String] =
      Some(HeroicCharacterClass.searchPrefixSource)

    /**
     * @inheritdoc
     */
    override protected def nameSource: String = id.splitByCase.toPascalCase

    override def displayText: String = s"$id: $level"

    override def numericalSortKey: Int = level

    override def alphaSortKey: String = id
  }

  /**
   * Represents the minimum level of a given class to qualify
   *
   * @param id
   *   the patron / faction
   * @param level
   *   the minimum amount of favor required
   */
  case class ReqFavorPatron(id: String, level: Int) extends Requirement with NumberRequirementSort {
    override def prefix: Option[String] = Some(FavorPatron.searchPrefixSource)

    /**
     * @inheritdoc
     */
    override protected def nameSource: String = id.splitByCase.toPascalCase

    override def numericalSortKey: Int = level

    override def alphaSortKey: String = id
  }

  /**
   * Represents a prerequisite enhancement, generally used as a prerequisite for a higher level
   * enhancement.
   *
   * @param id
   *   the id of the enhancement
   */
  case class ReqEnhancement(id: String) extends Requirement {
    override def prefix: Option[String] = Some("Enhancement")

    override def alphaSortKey: String = id

    /**
     * @inheritdoc
     */
    override protected def nameSource: String = id.splitByCase.toPascalCase
  }

  case class ReqClassEnhancement(id: String) extends Requirement {
    override def prefix: Option[String] = Some("ClassEnhancement")

    override def alphaSortKey: String = id

    /**
     * Sets or maps the source text for the DisplayName.
     *
     * @return
     *   Source text.
     */
    override protected def nameSource: String = id.splitByCase.toPascalCase
  }

  /**
   * Represents a particular alignment, i.e. A Pure Good weapon requires
   * [[io.truthencode.ddo.model.alignment.MoralAxis.Good]] (Either the law or moral axis) Some
   * items, such as Generated armors such as Armors of Stability give bonus if the character is
   * [[io.truthencode.ddo.model.alignment.Alignments.TrueNeutral]].
   *
   * @param id
   *   either a fully qualified Alignment (i.e. ChaoticGood) or one of the axis, i.e. Lawful or Evil
   */
  case class ReqAlignment(id: Either[AlignmentType, Alignments]) extends Requirement {

    override def alphaSortKey: String = nameSource

    /**
     * Prefixes the value with "Align" keyword.
     *
     * @return
     *   the Prefix based on Alignment type and scope
     * @todo
     *   Prefix based on Axis verses fully qualified alignment
     */
    override def prefix: Option[String] = Some("Align")

    /**
     * @inheritdoc
     */
    override protected def nameSource: String = id match {
      case Left(x: LawAxis) =>
        s"Align:{${x.entryName.splitByCase.toPascalCase}}"
      case Left(x: MoralAxis) =>
        s"Align:{${x.entryName.splitByCase.toPascalCase}}"
      case Right(x) => s"Align:${x.entryName.splitByCase.toPascalCase}"
    }

  }

  /**
   * Represents the minimum Guild level a player must be to equip an item.
   *
   * @param id
   *   The level of the Guild the player must be a member of
   * @note
   *   These items are generally out of game.
   */
  case class ReqGuildLevel(id: Int) extends Requirement with NumberRequirementSort {
    override def numericalSortKey: Int = id

    override def alphaSortKey: String = nameSource

    override def prefix: Option[String] = None

    /**
     * @inheritdoc
     */
    override protected def nameSource: String = "GuildLevel"
  }

  /**
   * Represents the minimum level of the skill to activate an ability or qualify for an enhancement.
   *
   * @param id
   *   Identifies the skill, i.e. 'Bluff'
   * @param amount
   *   the minimum trained or effective level of the skill.
   * @param trained
   *   determines if the skill must be trained to this level or simply a total value after buffs,
   *   equipment, etc. true (i.e. the base skill must be explicitly trained to this level.) false
   *   the total effective skill must be this level. i.e. can include bonuses from items / ability
   *   scores etc.
   */
  case class ReqSkill(id: String, amount: Int, trained: Boolean = false)
    extends Requirement with NumberRequirementSort {
    override def prefix: Option[String] = Some(Skill.searchPrefixSource)

    override def alphaSortKey: String = id

    override def displayText: String = {
      val pf = if (trained) s"(Trained) " else ""
      s"${pf}Ranks in $id: $amount"
    }

    /**
     * @inheritdoc
     */
    override protected def nameSource: String = id.splitByCase.toPascalCase

    override def numericalSortKey: Int = amount
  }

  /**
   * Represents the minimum total character level required to activate or select a skill, wear an
   * item etc.
   *
   * @param characterLevel
   *   Minimum character level.
   */
  case class ReqCharacterLevel(characterLevel: Int) extends Requirement with NumberRequirementSort {
    override def numericalSortKey: Int = characterLevel

    override def alphaSortKey: String = entryName

    override def prefix: Option[String] = None

    /**
     * @inheritdoc
     */
    override protected def nameSource: String = "CharacterLevel"

    override def displayText: String = entryName + s": $characterLevel"
  }

  /**
   * Represents a Feat character must possess in order to use / attain
   *
   * @param id
   *   Name of the Feat
   */
  case class ReqFeat(id: String) extends Requirement {
    override def prefix: Option[String] = Some(Feat.searchPrefixSource)

    override def alphaSortKey: String = id

    /**
     * @inheritdoc
     */
    override protected def nameSource: String = id.splitByCase.toPascalCase

    override def displayText: String = Feat.withName(id).displayText
  }

  /**
   * Represents an Attribute (i.e. Strength / Dexterity etc.)
   *
   * @param id
   *   Name of attribute
   * @param level
   *   Minimum level of the given Attribute.
   */
  case class ReqAttribute(id: String, level: Int) extends Requirement with NumberRequirementSort {
    override def numericalSortKey: Int = level

    override def alphaSortKey: String = id

    override def prefix: Option[String] = Option(Attribute.searchPrefixSource)

    /**
     * @inheritdoc
     */
    override protected def nameSource: String = id.splitByCase.toPascalCase

    override def displayText: String = s"$id: $level"
  }

  case class ReqPointsInTree(treeLike: TreeLike, amount: Int) extends Requirement {
    val pointType: SpendablePoints = treeLike.pointType

    override def alphaSortKey: String = treeLike.entryName

    override def prefix: Option[String] = Some(treeLike.searchPrefix)

    /**
     * Sets or maps the source text for the DisplayName.
     *
     * @return
     *   Source text.
     */
    override protected def nameSource: String = treeLike.entryName.splitByCase.toPascalCase
  }

  case class ReqPoints(id: String, amount: Int) extends Requirement {
    override def prefix: Option[String] = Some(SpendablePoints.searchPrefix)

    override def alphaSortKey: String = id

    /**
     * Sets or maps the source text for the DisplayName.
     *
     * @return
     *   Source text.
     */
    override protected def nameSource: String = id.splitByCase.toPascalCase
  }
}
