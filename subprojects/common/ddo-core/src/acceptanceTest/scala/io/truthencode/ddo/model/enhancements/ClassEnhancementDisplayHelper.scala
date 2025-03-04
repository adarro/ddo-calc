/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ClassEnhancementDisplayHelper.scala
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
import io.truthencode.ddo.support.requisite.{
  ActionPointRequisite,
  PointInTreeRequisite,
  PointsAvailableRequisite,
  RequiresActionPoints
}

trait ClassEnhancementDisplayHelper extends EnhancementDisplayHelper with LazyLogging {
  type ENH = ClassEnhancement & Tier & ActionPointRequisite & PointInTreeRequisite
  lazy val mappedValues: Map[String, ClassEnhancementInfo] = {

    val ee = ClassEnhancement.values.collect {
      case x: (ClassEnhancement & Tier & ClassBasedEnhancements & PointInTreeRequisite &
            PointsAvailableRequisite & RequiresActionPoints) if x.tree == tree =>
        x
    }
    logger.info(s"Display Helper loaded ${ee.size} values for ${tree.displayText}")
    ee.map { v =>
      logger.info(s"Loading enhancement with entry id: ${v.entryName} with key ${v.displayText}")

      // val c = ClassEnhancementInfo.apply(v.entryName,Some(v.displayText))
      val c = ClassEnhancementInfo.apply(v)
      logger.info(s"Added using key ${c.name}")
      c.name -> c
    }.toMap
  }
  override val displayEnum: E = ClassEnhancement
  val values: Seq[ENH] = displayEnum.values.collect { case x: ENH => x }

}
