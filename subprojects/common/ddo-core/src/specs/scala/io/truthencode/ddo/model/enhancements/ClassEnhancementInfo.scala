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
package io.truthencode.ddo.model.enhancements

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.enhancement.{ClassBasedEnhancements, Tier}
import io.truthencode.ddo.model.enhancement.enhancements.ClassEnhancement
import io.truthencode.ddo.support.requisite.{
  ActionPointRequisite,
  PointInTreeRequisite,
  PointsAvailableRequisite,
  RequiresActionPoints
}
import io.truthencode.ddo.support.StringUtils._

trait ClassEnhancementInfo {
  type ENH = ClassEnhancement with Tier with ActionPointRequisite with PointInTreeRequisite
  val values: Seq[ENH] = ClassEnhancement.values collect { case x: ENH => x }
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

  def apply(name: String): ClassEnhancementInfo = {
    val srch = name.symbolsToWords.filterAlphaNumeric
    logger.info(s"Searching $name using: $srch")
    val eOpt = ClassEnhancement.withNameInsensitiveOption(srch) match {
      case Some(
          x: ClassEnhancement with Tier with ClassBasedEnhancements with PointInTreeRequisite with PointsAvailableRequisite with RequiresActionPoints
          ) =>
        logger.info(s"Found ${x.displayText} => ${x.entryName}")
        Some(x)
      case _ =>
        logger.warn(s"Failed to locate ClassEnhancement with Name $name")
        None

    }
    if (eOpt.nonEmpty) {
      val e = eOpt.get

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
        case _       => 0
      } // .flatMap{x => x._3} //.flatMap {p => p._3}
      val requirements: Option[List[String]] = None

      val description: String = e.rawDescription
      CEnhancementDumb(name, id, actionPointCost, ranks, progression, requirements, description)
    } else {
      CEnhancementDumb(name)
    }

  }
}

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
    extends ClassEnhancementInfo
    with LazyLogging {

  private def _enh = {
    logger.info(s"locating values with entryname eq $id")
    val v = values.find(p => p.entryName.equals(identifier))
    logger.info(s"result ${v}")
    v
  }
  //  require(_enh.nonEmpty,s"No value found matching Id $id")

  private val enh: ENH = _enh.get

  /**
    * The string id used to create the object
    */
  override def id: String = enh.entryName
  override val actionPointCost: Int = enh.apCostPerRank
  override val ranks: Int = enh.ranks

  override val progression: Int = enh.progressionInTree.find { p =>
    p._1 == enh.tree
  } match {
    case Some(x) => x._3
    case _       => 0
  } // .flatMap{x => x._3} //.flatMap {p => p._3}
  override val requirements: Option[List[String]] = None

  override val description: String = enh.rawDescription

}
