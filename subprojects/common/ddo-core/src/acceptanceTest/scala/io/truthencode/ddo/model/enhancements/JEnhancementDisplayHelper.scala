/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: JEnhancementDisplayHelper.scala
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
import io.truthencode.ddo.model.enhancement.Tier
import io.truthencode.ddo.support.tree.ClassTrees

import scala.beans.BeanProperty

abstract class JEnhancementDisplayHelper extends ClassEnhancementDisplayHelper with LazyLogging {

  override lazy val tree: ClassTrees = ClassTrees.withName(treeId)

  /**
   * Java Work-around to set
   */
  @BeanProperty
  var treeId: String = scala.compiletime.uninitialized
  @BeanProperty
  var currentTier: String = scala.compiletime.uninitialized

  @BeanProperty
  var currentEnhancement: String = scala.compiletime.uninitialized

  // [enhancement] | [Description][description] | [AP_Cost][apcost] | [Ranks][ranks] | [Progression][progression]| [Requirements][requirements]|
  def tier: Tier =
    Tier
      .withNameOption(currentTier)
      .getOrElse({
        logger.error(
          s"Could not find a valid Tier using ID $currentTier defaulting to ${Tier.Core.entryName}"
        )
        Tier.Core
      })

  def loadFromKey(enhancementId: String): ResultObject = {
    val trimmed = enhancementId.trim
    logger.info(
      s"************* Attempting to load Enhancement ResultObject using key: [$trimmed]"
    )
    // logger.info(s"mappvalues has ${mappedValues.size} elements")
    val ce: ClassEnhancementInfo =
      mappedValues.getOrElse(
        trimmed, {
          logger.warn(s"Failed to find Enhancement with id $enhancementId")
          CEnhancementDumb(enhancementId)
        })
    logger.debug(ce.toString)
    implicit val altName: Option[String] = Some(enhancementId)
    ResultObject.apply(ce)

  }

  sealed trait prefixes {
    val text: String
    val separator: String = ": "

    def prefix(value: Int): String = prefix(value.toString)

    def prefix(value: String): String = {
      s"$text$separator$value"
    }
  }

  case class ResultObject(
    enhancement: String,
    description: String,
    apcost: String,
    ranks: String,
    progression: String,
    requirements: String
  )

  case object Prefix_AP extends prefixes {
    override val text: String = "AP Cost"
  }

  case object Prefix_Rank extends prefixes {
    override val text: String = "Ranks"
  }

  case object Prefix_Progression extends prefixes {
    override val text: String = "Progression"
  }

  case object Prefix_Requires extends prefixes {
    override val text: String = "Requires"
  }

  object ResultObject {

    def apply(
      cc: ClassEnhancementInfo
    )(implicit altNameText: Option[String] = None): ResultObject = {
      ResultObject(
        enhancement = altNameText.getOrElse(cc.name),
        description = cc.description,
        apcost = Prefix_AP.prefix(cc.actionPointCost),
        ranks = Prefix_Rank.prefix(cc.ranks),
        progression = Prefix_Progression.prefix(cc.progression),
        requirements = Prefix_Requires.prefix("")
      )

    }
  }

}
