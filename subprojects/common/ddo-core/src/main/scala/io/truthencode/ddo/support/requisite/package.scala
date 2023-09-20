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
package io.truthencode.ddo.support

import enumeratum.EnumEntry
import io.truthencode.ddo.model.alignment.{AlignmentType, Alignments}
import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.enhancement.enhancements.ClassEnhancement
import io.truthencode.ddo.model.favor.FavorPatron
import io.truthencode.ddo.model.feats.{ClassFeat, Feat, GeneralFeat, RacialFeat}
import io.truthencode.ddo.model.race.Race
import io.truthencode.ddo.model.skill.Skill
import io.truthencode.ddo.support.points.SpendablePoints
import io.truthencode.ddo.support.requisite.Requirement._
import io.truthencode.ddo.support.tree.TreeLike

/**
 * Created by adarro on 1/30/2017. This package contains Requirement logic for applying constraints
 * and limits to Entities such as Minimum Class Level or a required alignment / class etc.
 * Convenience and implicit conversions
 */
package object requisite {
  type Result = (Boolean, Option[List[Requisite]])

  /**
   * Default grouping key. Assumes a required value when none is specified, thus it is effectively
   * equivalent to [[requiredGroupKey]] at this time, but may change in the future.
   */
  final val defaultGroupKey = "Prerequisite"

  /**
   * This constant represents Required Groupings
   */
  final val requiredGroupKey = defaultGroupKey
  object RequirementImplicits {

    implicit class PatronImplicits(val source: (FavorPatron, Int)) {
      def toReqFavor: ReqFavorPatron = patronToReq(source)
    }

    val patronToReq: PartialFunction[(FavorPatron, Int), ReqFavorPatron] =
      new PartialFunction[(FavorPatron, Int), ReqFavorPatron] {

        override def isDefinedAt(x: (FavorPatron, Int)): Boolean =
          Requirement
            .withNameOption(s"${FavorPatron.searchPrefix}${x._1.entryName}")
            .isDefined

        override def apply(v1: (FavorPatron, Int)): ReqFavorPatron =
          ReqFavorPatron(v1._1.entryName, v1._2)
      }

    implicit class SkillImplicits(val source: (Skill, Int)) {
      def toReq: ReqSkill = skillToReq(source)
    }

    val skillToReq: PartialFunction[(Skill, Int), ReqSkill] =
      new PartialFunction[(Skill, Int), ReqSkill] {

        override def isDefinedAt(x: (Skill, Int)): Boolean =
          Requirement
            .withNameOption(s"${Skill.searchPrefix}${x._1.entryName}")
            .isDefined

        override def apply(v1: (Skill, Int)): ReqSkill =
          ReqSkill(v1._1.entryName, v1._2)
      }

    implicit class AttributeImplicits(val source: (Attribute, Int)) {
      def toReq: ReqAttribute = ReqAttribute(source._1.toString, source._2)
    }

    val attrToReq: PartialFunction[(Attribute, Int), ReqAttribute] =
      new PartialFunction[(Attribute, Int), ReqAttribute] {

        override def isDefinedAt(x: (Attribute, Int)): Boolean =
          Requirement
            .withNameOption(s"${Attribute.searchPrefix}${x._1.entryName}")
            .isDefined

        override def apply(v1: (Attribute, Int)): ReqAttribute =
          ReqAttribute(v1._1.entryName, v1._2)
      }

    implicit class RaceImplicits(val source: (Race, Int)) {
      def toReq: ReqRace = raceToReq(source)
    }

    val raceToReq: PartialFunction[(Race, Int), ReqRace] =
      new PartialFunction[(Race, Int), ReqRace] {

        override def isDefinedAt(x: (Race, Int)): Boolean =
          Requirement
            .withNameOption(s"${Race.searchPrefix}${x._1.entryName}")
            .isDefined

        override def apply(v1: (Race, Int)): ReqRace =
          ReqRace(v1._1.entryName, v1._2)
      }

    implicit class FeatImplicits(val source: Feat with EnumEntry) {
      def toReq: ReqFeat = ReqFeat(source.entryName)
    }

    val matchGeneralFeat: PartialFunction[Feat, GeneralFeat] = { case x: GeneralFeat =>
      x
    }

    val matchClassFeat: PartialFunction[Feat, ClassFeat] = { case x: ClassFeat =>
      x
    }

    val matchRacialFeat: PartialFunction[Feat, RacialFeat] = { case x: RacialFeat =>
      x
    }

//    val matchGFeatId : PartialFunction[String,GeneralFeat] = {
//      case x:String if GeneralFeat.namesToValuesMap.contains(x) => GeneralFeat.withNameOption(x).get
//    }
//
//    val matchGFeatText = new PartialFunction[String,GeneralFeat] {
//      override def isDefinedAt(x: String): Boolean = GeneralFeat.exists(x)
//
//      override def apply(v1: String): GeneralFeat = ???
//    }

    val featToReq: PartialFunction[Feat, ReqFeat] = new PartialFunction[Feat, ReqFeat] {

      override def isDefinedAt(x: Feat): Boolean =
        Requirement
          .withNameOption(s"${Feat.searchPrefix}${x.entryName}")
          .isDefined

      override def apply(v1: Feat): ReqFeat = ReqFeat(v1.entryName)
    }

    val classEnhancementToReq: PartialFunction[ClassEnhancement, ReqClassEnhancement] =
      new PartialFunction[ClassEnhancement, ReqClassEnhancement] {
        override def isDefinedAt(x: ClassEnhancement): Boolean =
          Requirement
            .withNameOption(s"${ClassEnhancement.searchPrefix}${x.entryName}")
            .isDefined

        override def apply(v1: ClassEnhancement): ReqClassEnhancement = ReqClassEnhancement(
          v1.entryName)
      }
//    val anyFeatToReq = new PartialFunction[Feat, ReqFeat] {
//      override def isDefinedAt(x: Feat): Boolean =
//        Requirement
//          .withNameOption(s"${Feat.searchPrefix}${x.entryName}")
//          .isDefined
//
//      override def apply(v1: Feat): ReqFeat = ReqFeat(v1.entryName)
//    }
    val racialFeatToReq: PartialFunction[RacialFeat, ReqFeat] =
      new PartialFunction[RacialFeat, ReqFeat] {

        override def isDefinedAt(x: RacialFeat): Boolean =
          Requirement
            .withNameOption(s"${RacialFeat.searchPrefix}${x.entryName}")
            .isDefined

        override def apply(v1: RacialFeat): ReqFeat = ReqFeat(v1.entryName)
      }

    val classFeatToReq: PartialFunction[ClassFeat, ReqFeat] =
      new PartialFunction[ClassFeat, ReqFeat] {

        override def isDefinedAt(x: ClassFeat): Boolean =
          Requirement
            .withNameOption(s"${ClassFeat.searchPrefix}${x.entryName}")
            .isDefined

        override def apply(v1: ClassFeat): ReqFeat = ReqFeat(v1.entryName)
      }

    implicit class ClassImplicits(val source: (HeroicCharacterClass, Int)) {
      def toReq: ReqClass = ReqClass(source._1.toString, source._2)
    }

//    val characterLevelToReq: PartialFunction[(Int,Int), ReqCharacterLevel] =
//        new  PartialFunction[(Int,Int), ReqCharacterLevel] {
//            override def isDefinedAt(x: (Int, Int)): Boolean = ???
//
//            override def apply(v1: (Int, Int)): ReqCharacterLevel = ???
//        }

    def classToReq(hc: Tuple2[HeroicCharacterClass, Int]*): Seq[ReqClass] = {
      hc.map { h =>
        ReqClass(h._1.toString, h._2)
      }
    }
    val classToReqOld: PartialFunction[(HeroicCharacterClass, Int), ReqClass] =
      new PartialFunction[(HeroicCharacterClass, Int), ReqClass] {

        override def isDefinedAt(x: (HeroicCharacterClass, Int)): Boolean =
          Requirement
            .withNameOption(s"${HeroicCharacterClass.searchPrefix}${x._1.entryName}")
            .isDefined

        override def apply(v1: (HeroicCharacterClass, Int)): ReqClass =
          ReqClass(v1._1.entryName, v1._2)
      }

    val alignmentTypeToReq: PartialFunction[AlignmentType, ReqAlignment] = {
      case x: AlignmentType =>
        ReqAlignment(Left(x))
    }

    val alignmentsToReq: PartialFunction[Alignments, ReqAlignment] = { case x: Alignments =>
      ReqAlignment(Right(x))
    }

    val characterLevelToReq: PartialFunction[Int, ReqCharacterLevel] = {
      case x: Int if CharacterLevels contains x => ReqCharacterLevel(x)
    }

    val pointToReq: PartialFunction[(SpendablePoints, Int), ReqPoints] = {
      case x: (SpendablePoints, Int) =>
        ReqPoints(x._1.entryName, x._2)
    }

    val progressionToReq: PartialFunction[(TreeLike, Int), ReqPointsSpentInTree] = {
      case (x: TreeLike, y: Int) =>
        ReqPointsSpentInTree(x, y)

//              if Points.withNameOption(x._1.entryName).nonEmpty=> x._1 match {
//              case y: ActionPoints => ReqPointsSpent(Points.ActionPoints,x._2)
//              case y: SurvivalPoints => ReqPointsSpent(Points.SurvivalPoints,x._2)
//              case y: EpicDestinyPoints => ReqPointsSpent(Points.EpicDestinyPoints,x._2)
//          }
    }

    val progressionWithPointsToReq
      : PartialFunction[(TreeLike, SpendablePoints, Int), ReqPointsSpentInTree] = {
      case (x: TreeLike, _, y: Int) => ReqPointsSpentInTree(x, y)
    }
//    val pointsToReq: PartialFunction[(Points with SpendablePoint,Int),ReqPointsSpent] = {
//        case x: (Points with SpendablePoint,Int) => {
//            x._1
//            ReqPointsSpent(Points.ActionPoints,x.amount)
//        }
//    }

  }

}
