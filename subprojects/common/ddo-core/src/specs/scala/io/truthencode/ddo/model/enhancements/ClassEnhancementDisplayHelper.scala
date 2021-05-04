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
import io.truthencode.ddo.model.enhancement.Tier
import io.truthencode.ddo.model.enhancement.enhancements.ClassEnhancement
import io.truthencode.ddo.support.requisite.{ActionPointRequisite, PointInTreeRequisite}

trait ClassEnhancementDisplayHelper extends EnhancementDisplayHelper with LazyLogging {
  override val enum: E = ClassEnhancement
  type ENH = ClassEnhancement with Tier with ActionPointRequisite with PointInTreeRequisite

  val values: Seq[ENH] = enum.values collect { case x: ENH => x }

  lazy val mappedValues: Map[String, ClassEnhancementInfo] = values.map { v =>
    logger.info(s"Loading enhancement with entry: ${v.entryName}")
    val c = ClassEnhancementInfo.apply(v.displayText)
    logger.info(s"Added using key ${c.name}")
    c.name -> c
  }.toMap

}
