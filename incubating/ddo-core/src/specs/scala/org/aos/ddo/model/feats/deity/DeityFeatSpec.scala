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
package org.aos.ddo.model.feats.deity

import java.util

import com.typesafe.scalalogging.LazyLogging
import org.aos.ddo.model.feats._
import org.aos.ddo.model.item.weapon.FavoredWeapon
import org.aos.ddo.model.religions.Religion
import org.aos.ddo.model.worlds.World
import org.aos.ddo.support.StringUtils.Extensions
import org.aos.ddo.support.naming.{DisplayName, Prefix}
import org.concordion.api.FullOGNL
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

import scala.collection.immutable
import scala.collection.JavaConverters._

@FullOGNL
@RunWith(classOf[ConcordionRunner])
class DeityFeatSpec extends DisplayHelper with LazyLogging {

  private val filterEberron
  : PartialFunction[Entry, Entry with ReligionFeatBase] = {
    case x: EberronReligionBase with ReligionFeatBase => x
  }

  private val filterForgottenRealms
  : PartialFunction[Entry, Entry with ReligionFeatBase] = {
    case x: ForgottenRealmsReligionBase with ReligionFeatBase => x
  }

  private val filterFollower: PartialFunction[Entry, Entry] = {
    case x: FollowerOfLevel => x
  }

  private val filterChild: PartialFunction[Entry, Entry] = {
    case x: ChildOfLevel => x
  }

  private val filterBeloved: PartialFunction[Entry, Entry] = {
    case x: BelovedOfLevel => x
  }

  private val filterUnique: PartialFunction[Entry, Entry] = {
    case x: UniqueLevel => x
  }

  private val filterDR
  : PartialFunction[Entry, Entry with DisplayName with Prefix] = {
    case x: DamageReductionLevel with DisplayName with Prefix => x
  }

  private val filterFavoredWeapons
  : PartialFunction[Entry, Entry with FavoredWeapon] = {
    case x: FavoredWeapon => x
  }

  implicit class ListEntryOpts(source: Option[Entry]) {
    def firstStringValue: String = {
      source match {
        case Some(x) => x.displayText
        case _ => ""
      }
    }
  }

  override def verify(): util.List[String] = {
    enum.values.map(_.displayText).asJava
  }

  var instanceWorld: Option[World] = None

  def setUpWorld(TEXT: String): Unit = {
    instanceWorld = World.withNameOption(TEXT.toPascalCase)
  }

  def findReligion(id: String): Option[Religion] = Religion.withNameOption(id)

  case class ReligionWeapon(r: FavoredWeapon, re: Religion)

  def myFilter(f: List[Entry with ReligionFeatBase],
               rOpt: Option[Religion]): List[Entry] = {
    for {
      x <- f
      o <- x.allowedReligions
      if rOpt.contains(o)
    } yield x
  }

  def loadFromKey(data: String): ResultObject = {
    val dataS = data.toPascalCase
    logger.debug(
      s"Attempting to find religion $dataS for world $instanceWorld")
    val worldReligion: immutable.Seq[Entry with ReligionFeatBase] =
      instanceWorld match {
        case Some(World.Eberron) => enum.values collect filterEberron
        case Some(World.ForgottenRealms) =>
          enum.values collect filterForgottenRealms
        case _ => Nil
      }
    logger.debug(
      s"Located ${worldReligion.size} matching world religion feats for $instanceWorld")
    val oRel: Option[Religion] = findReligion(dataS)
    logger.debug(s"Religion $oRel")

    val f = myFilter(worldReligion.toList, oRel)
    logger.debug(s"Located ${f.size} matching feats for $dataS $f")

    val fw: String = oRel match {
      case Some(x: FavoredWeapon) => x.favoredWeapon.displayText
    }

    val follower = (f collectFirst filterFollower).firstStringValue
    val beloved = (f collectFirst filterBeloved).firstStringValue
    val child = (f collectFirst filterChild).firstStringValue
    val unique = (f collectFirst filterUnique).firstStringValue
    val drFeats = f collect filterDR
    val k = for {
      x <- drFeats
      p <- x.withPrefix
    } yield x.displaySource.replace(p, "")
    logger.debug(s"DR Feats $drFeats")
    logger.debug(s"DR Feats (Mapped) $k")
    val prefix = "Damage Reduction: "
    val dr: String = if (k.nonEmpty) {
      k.sorted.mkString(start = prefix, sep = ", ", end = "")
    } else {
      s"$prefix TBD"
    }
    val result = ResultObject(religion = data,
      follow = follower,
      favoredWeapon = fw,
      child = child,
      unique = unique,
      beloved = beloved,
      damageReduction = dr)
    logger.debug(s"Result: $result")
    result

  }

  case class ResultObject(religion: String,
                          favoredWeapon: String,
                          follow: String,
                          child: String,
                          unique: String,
                          beloved: String,
                          damageReduction: String)

  override val enum: E = Feat
}
