/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ClassEnhancementInfo.scala
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
package io.truthencode.ddo.model.enhancements

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.enhancement.enhancements.ClassEnhancement
import io.truthencode.ddo.model.enhancement.{ClassBasedEnhancements, Tier}
import io.truthencode.ddo.support.StringUtils._
import io.truthencode.ddo.support.requisite.{
  ActionPointRequisite,
  PointInTreeRequisite,
  PointsAvailableRequisite,
  RequiresActionPoints
}

trait ClassEnhancementInfo {
  type ENH = ClassEnhancement & Tier & ActionPointRequisite & PointInTreeRequisite
  val values: Seq[ENH] = ClassEnhancement.values.collect { case x: ENH => x }
  val name: String
  val actionPointCost: Int
  val ranks: Int
  val progression: Int
  val requirements: Option[List[String]]
  val description: String

  /**
   * This should == ClassEnhancement.entryName
   */
  def id: String
}

object ClassEnhancementInfo extends LazyLogging {

// scalastyle:off
  def apply(
    classEnhancement: ClassEnhancement & Tier & ClassBasedEnhancements & PointInTreeRequisite &
      PointsAvailableRequisite & RequiresActionPoints
  ): CEnhancementDumb = {
    val e = classEnhancement
    def id: String = e.entryName
    val actionPointCost: Int = e.apCostPerRank
    val ranks: Int = e.ranks
    val key = e.displayText
    val progression: Int = e.progressionInTree.find { p =>
      p._1 == e.tree
    } match {
      case Some(x) => x._3
      case _ => 0
    } // .flatMap{x => x._3} //.flatMap {p => p._3}
    val requirements: Option[List[String]] = None

    val description: String = e.rawDescription
    CEnhancementDumb(key, id, actionPointCost, ranks, progression, requirements, description)
  }

  def apply(key: String, fullText: Option[String] = None): ClassEnhancementInfo = {
    val srch = key.symbolsToWords.filterAlphaNumeric
    logger.info(s"Searching $key using: $srch")
    val eOpt = ClassEnhancement.withNameInsensitiveOption(srch) match {
      case Some(
            x: (ClassEnhancement & Tier & ClassBasedEnhancements & PointInTreeRequisite &
              PointsAvailableRequisite & RequiresActionPoints)
          ) =>
        logger.info(s"Found ${x.displayText} => ${x.entryName}")
        Some(x)
      case _ =>
        fullText match {
          case Some(y) =>
            logger.warn(
              s"Failed to locate ClassEnhancement with key $key, attempting using fullText $fullText"
            )
            ClassEnhancement.values.map { v =>
              v.displayText -> v.entryName
            }
              .find(_._1 == y) match {
              case Some(value) =>
                logger.info(s"located valid key from fulltext, recursing using ${value._2}")
                apply(value._2)
              case None =>
                logger.warn("Could not locate $key")
                None
            }
          case None =>
            logger.warn("could not locate with key $key and no alternate supplied")
            None
        }
        None
    }
    if eOpt.nonEmpty then {
      val e = eOpt.get
      logger.info(s"located ${e.displayText}")

      /**
       * The string id used to create the object
       */
      def id: String = e.entryName
      val actionPointCost: Int = e.apCostPerRank
      val ranks: Int = e.ranks

      val progression: Int = e.progressionInTree.find { p =>
        p._1 == e.tree
      } match {
        case Some(x) => x._3
        case _ => 0
      } // .flatMap{x => x._3} //.flatMap {p => p._3}
      val requirements: Option[List[String]] = None

      val description: String = e.rawDescription
      CEnhancementDumb(key, id, actionPointCost, ranks, progression, requirements, description)
    } else {
      logger.warn(s"did not locate id, using safe wrapper for $key")
      CEnhancementDumb(key)
    }

  }
  // scalastyle:off number.of.methods
}

/**
 * Internal enhancement wrapper for ClassEnhancementInfo
 * @param name
 *   The name of the enhancement
 * @param id
 *   The id of the enhancement
 * @param actionPointCost
 *   The action point cost for the enhancement
 * @param ranks
 *   The number of ranks the enhancement has
 * @param progression
 *   point progression in the tree
 * @param requirements
 *   Requirements if any for the enhancement
 * @param description
 *   The description of the enhancement
 */
case class CEnhancementDumb(
  name: String,
  id: String = "",
  actionPointCost: Int = 0,
  ranks: Int = 0,
  progression: Int = 0,
  requirements: Option[List[String]] = None,
  description: String = ""
) extends ClassEnhancementInfo

case class CEnhancement(
  name: String
)(implicit identifier: String = name.toPascalCase.filterAlphaNumeric)
  extends ClassEnhancementInfo with LazyLogging {
  private val enh: ENH = _enh.get
  override val actionPointCost: Int = enh.apCostPerRank

  override val ranks: Int = enh.ranks
  override val progression: Int = enh.progressionInTree.find { p =>
    p._1 == enh.tree
  } match {
    case Some(x) => x._3
    case _ => 0
  } // .flatMap{x => x._3} //.flatMap {p => p._3}
  override val requirements: Option[List[String]] = None
  override val description: String = enh.rawDescription

  private def _enh = {
    logger.info(s"locating values with entryname eq $id")
    val v = values.find(p => p.entryName.equals(identifier))
    logger.info(s"result $v")
    v
  }

  /**
   * The string id used to create the object
   */
  override def id: String = enh.entryName

}
