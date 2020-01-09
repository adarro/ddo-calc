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
package org.aos.ddo.model.feats.epic

import java.util

import com.typesafe.scalalogging.LazyLogging
import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.model.classes.HeroicCharacterClass
import org.aos.ddo.model.feats.{ClassDisplayHelper, ClassRestricted, EpicFeatDisplayHelper, SubFeatInformation}
import org.aos.ddo.support.naming.FriendlyDisplay
import org.aos.ddo.support.requisite.ClassRequisite
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

import scala.collection.JavaConverters._

@RunWith(classOf[ConcordionRunner])
class EpicClassFeatSpec
  extends LazyLogging {
  type Entry = EnumEntry with SubFeatInformation with FriendlyDisplay
  type E = Enum[_ <: Entry]
  type CharClass = Option[HeroicCharacterClass]
  type EpicClassHelper = ClassDisplayHelper with EpicFeatDisplayHelper

  //  override val cClass: CharacterClass =
  //    instanceClass.getOrElse(CharacterClass.Artificer)
  //  override val filterByCategory: PartialFunction[Entry, Entry] = {
  //    case x: ClassRestricted => x
  //  }

  val helperMap: scala.collection.mutable.HashMap[CharClass, EpicClassHelper] = scala.collection.mutable.HashMap.empty
  val filterByAnyOfs: PartialFunction[Entry, Entry] = {
    case x: ClassRequisite if x.anyOfClass.exists(findHelper.isDefinedForClass(_)) => x
  }
  private var instanceClass: Option[HeroicCharacterClass] = None

  def setUpClass(classId: String): Unit = {
    logger.info(s"classId $classId")
    val result = HeroicCharacterClass.namesToValuesMap
      .filterKeys(_ == classId)
      .values
      .headOption
    logger.info(s"classId $classId Set instance to $result")
    instanceClass = result

  }

  def verify(): util.List[String] = {
    logger.info(s"Verify instance $instanceClass")
    val h = findHelper

    val y: Seq[Entry] = h.enum.values collect h.existing
    logger.info(s"count from existing ${y.size}")
    val z = y collect h.filterByCategory
    logger.info(s"count from filterByCategory ${z.size}")
    z.map(_.displayText).asJava
  }

  def findHelper(implicit charClass: CharClass = instanceClass): EpicClassHelper = {
    helperMap.getOrElseUpdate(charClass, makeHelper(charClass))
  }

  def makeHelper(clazz: CharClass): EpicClassHelper = {
    new ClassDisplayHelper with EpicFeatDisplayHelper {
      override val cClass: HeroicCharacterClass =
        instanceClass.getOrElse(HeroicCharacterClass.Artificer)
      override val filterByCategory: PartialFunction[Entry, Entry] = {
        case x: ClassRestricted => x
      }

      override def verify(): util.List[String] = {
        logger.info(s"Verify instance $instanceClass")

        val y: Seq[Entry] = enum.values collect existing
        logger.info(s"count from existing ${y.size}")
        val z = y collect filterByCategory
        logger.info(s"count from filterByCategory ${z.size}")
        z.map(_.displayText).asJava
      }
    }
  }

}
