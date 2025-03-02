/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: DeityFeatSpec.scala
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
package io.truthencode.ddo.model.feats.deity

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.feats._
import io.truthencode.ddo.model.item.weapon.FavoredWeapon
import io.truthencode.ddo.model.religions.Religion
import io.truthencode.ddo.model.worlds.World
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, Prefix}
import org.concordion.api.FullOGNL
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

import java.util
import scala.collection.immutable
import scala.jdk.CollectionConverters.SeqHasAsJava

@FullOGNL
@RunWith(classOf[ConcordionRunner])
class DeityFeatSpec extends FeatDisplayHelper with LazyLogging {

  override val displayEnum: E = Feat
  private val filterEberron: PartialFunction[Entry, Entry & ReligionFeatBase] = {
    case x: (EberronReligionBase & ReligionFeatBase) => x
  }
  private val filterForgottenRealms: PartialFunction[Entry, Entry & ReligionFeatBase] = {
    case x: (ForgottenRealmsReligionBase & ReligionFeatBase) => x
  }
  private val filterFollower: PartialFunction[Entry, Entry] = { case x: FollowerOfLevel =>
    x
  }
  private val filterChild: PartialFunction[Entry, Entry] = { case x: ChildOfLevel =>
    x
  }
  private val filterBeloved: PartialFunction[Entry, Entry] = { case x: BelovedOfLevel =>
    x
  }
  private val filterUnique: PartialFunction[Entry, Entry] = { case x: UniqueLevel =>
    x
  }
  private val filterDR: PartialFunction[Entry, Entry & DisplayName & Prefix] = {
    case x: (DamageReductionLevel & DisplayName & Prefix) => x
  }

  implicit class ListEntryOpts(source: Option[Entry]) {
    def firstStringValue: String = {
      source match {
        case Some(x) => x.displayText
        case _ => ""
      }
    }
  }
  private val filterFavoredWeapons: PartialFunction[Entry, Entry & FavoredWeapon] = {
    case x: FavoredWeapon =>
      x
  }
  var instanceWorld: Option[World] = None

  override def verify(): util.List[String] = {
    displayEnum.values.map(_.displayText).asJava
  }

  def setUpWorld(TEXT: String): Unit = {
    instanceWorld = World.withNameOption(TEXT.toPascalCase)
  }

  def loadFromKey(data: String): ResultObject = {
    val dataS = data.trim().toPascalCase
    logger.debug(s"Attempting to find religion $dataS for world $instanceWorld")
    val worldReligion: immutable.Seq[Entry & ReligionFeatBase] =
      instanceWorld match {
        case Some(World.Eberron) => displayEnum.values.collect(filterEberron)
        case Some(World.ForgottenRealms) =>
          displayEnum.values.collect(filterForgottenRealms)
        case _ => Nil
      }
    logger.debug(s"Located ${worldReligion.size} matching world religion feats for $instanceWorld")
    val oRel: Option[Religion] = findReligion(dataS)
    logger.debug(s"Religion $oRel")

    val f = myFilter(worldReligion.toList, oRel)
    logger.debug(s"Located ${f.size} matching feats for $dataS $f")

    val fw: String = oRel match {
      case Some(x: FavoredWeapon) => x.favoredWeapon.displayText
    }

    val follower = f.collectFirst(filterFollower).firstStringValue
    val beloved = f.collectFirst(filterBeloved).firstStringValue
    val child = f.collectFirst(filterChild).firstStringValue
    val unique = f.collectFirst(filterUnique).firstStringValue
    val drFeats = f.collect(filterDR)
    val k = for
      x <- drFeats
      p <- x.withPrefix
    yield x.displaySource.replace(p, "")
    logger.debug(s"DR Feats $drFeats")
    logger.debug(s"DR Feats (Mapped) $k")
    val prefix = "Damage Reduction: "
    val dr: String = if k.nonEmpty then {
      k.sorted.mkString(start = prefix, sep = ", ", end = "")
    } else {
      s"$prefix TBD"
    }
    val result = ResultObject(
      religion = data,
      follow = follower,
      favoredWeapon = fw,
      child = child,
      unique = unique,
      beloved = beloved,
      damageReduction = dr)
    logger.debug(s"Result: $result")
    result

  }

  def findReligion(id: String): Option[Religion] = Religion.withNameOption(id)

  def myFilter(f: List[Entry & ReligionFeatBase], rOpt: Option[Religion]): List[Entry] = {
    for
      x <- f
      o <- x.allowedReligions
      if rOpt.contains(o)
    yield x
  }

  case class ReligionWeapon(r: FavoredWeapon, re: Religion)

  case class ResultObject(
    religion: String,
    favoredWeapon: String,
    follow: String,
    child: String,
    unique: String,
    beloved: String,
    damageReduction: String)
}
