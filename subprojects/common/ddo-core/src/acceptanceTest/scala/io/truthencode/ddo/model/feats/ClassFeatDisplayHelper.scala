/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ClassFeatDisplayHelper.scala
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
package io.truthencode.ddo.model.feats

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.support.requisite.{ClassRequisite, SelectableToClass}

import java.util
import scala.jdk.CollectionConverters.SeqHasAsJava

/**
 * Created by Adarro on 3/5/2017.
 */
trait ClassFeatDisplayHelper extends FeatDisplayHelper with LazyLogging {

  type FNLevel = Entry => Seq[(HeroicCharacterClass, Int)]
  type FNClass = Entry => Seq[HeroicCharacterClass]
  lazy val classFeatByLevelMap: Seq[(String, Seq[Int])] = {
    val levels = for
      f <- displayEnum.values.filterNot(x => x.isInstanceOf[ParentFeat])
      e <- allTypesByLevelFilter(f)
    yield f.displayText -> e
    levels
      .groupBy(_._1)
      .map { x =>
        (x._1, x._2.map(_._2))
      }
      .to(Seq)
  }
  lazy val grantedFeats: util.List[String] = {
    val values = { displayEnum.values.collect(filterByGrantedTo) }.collect(filterByMainFeat)
    values.map(_.displayText).sorted.asJava
  }
  val cClass: HeroicCharacterClass
// Currently no sub-feats with Class based restrictions?
  val filterByAllOf: PartialFunction[Entry, Entry] = {
    case x: ClassRequisite if x.allOfClass.exists(isDefinedForClass(_)) => x
  }

  val filterByAnyOf: PartialFunction[Entry, Entry] = {
    case x: ClassRequisite if x.anyOfClass.exists(isDefinedForClass(_)) => x
  }

  val filterByGrantedTo: PartialFunction[Entry, Entry] = {
    case x: ClassRequisite if x.grantToClass.exists(isDefinedForClass(_)) => x
  }

  val filterByGrantedToByLevel: PartialFunction[(Entry, Int), (Entry, Int)] = {
    case (x: ClassRequisite, y) if x.grantToClass.exists(isDefinedForClass(_, Some(y))) =>
      (x, y)
  }

  val filterByClassBonusFeat: PartialFunction[Entry, Entry] = {
    case x: (BonusSelectableToClassFeat & SubFeatInformation)
        if x.bonusCharacterClass.contains(cClass) && !x.isSubFeat =>
      lazy val msg = s"Entry ${x.displayText} matched type and character class $cClass"
      lazy val idInfo = Map(
        "entryName" -> x.entryName,
        "displaySource" -> x.displaySource,
        "displayText" -> x.displayText
      )
      logger.debug(msg)
      if x.displayText.contains("Construct") then {
        logger.debug(s"Id Info $idInfo")
      }
      x
  }

  val filterByClassBonusFeatByLevel: PartialFunction[(Entry, Int), (Entry, Int)] = {
    case (x: (SelectableToClass & SubFeatInformation), y)
        if x.bonusSelectableToClass.exists(isDefinedForClass(_, Some(y))) && !x.isSubFeat =>
      (x, y)
  }

  val existing: PartialFunction[Entry, Entry] =
    filterByAllOf.orElse(filterByAnyOf).orElse(filterByGrantedTo)

  def isDefinedForClass(e: (HeroicCharacterClass, Int), level: Option[Int] = None): Boolean =
    e._1.eq(cClass) && isEqualOrEmpty(e._2, level)

  private def isEqualOrEmpty(i: Int, source: Option[Int]): Boolean =
    source.isEmpty || (source.nonEmpty && source.contains(i))

  def allTypesByLevelFilter(as: Entry): Seq[Int] =
    extractLevels(as, allOfFilterByLevel, anyOfFilterByLevel, grantToFilterByLevel)
      .map(_._2)

  def allOfFilterByLevel(as: Entry): Seq[(HeroicCharacterClass, Int)] = as match {
    case x: ClassRequisite => x.allOfClass.filter(_._1.eq(cClass))
  }

  def anyOfFilterByLevel(as: Entry): Seq[(HeroicCharacterClass, Int)] = as match {
    case x: ClassRequisite => x.anyOfClass.filter(_._1.eq(cClass))
  }

  def grantToFilterByLevel(as: Entry): Seq[(HeroicCharacterClass, Int)] = as match {
    case x: ClassRequisite => x.grantToClass.filter(_._1.eq(cClass))
  }

  def extractLevels(as: Entry, fn: FNLevel*): Seq[(HeroicCharacterClass, Int)] =
    fn.flatMap(f => f(as))

  def grantedFeatsByLevel(level: Int): util.List[String] = {
    val values = displayEnum.values.map((_, level)).collect(filterByGrantedToByLevel)
    values
      .filter(_._2 == level)
      .map(_._1.displayText)
      .sorted
      .asJava
  }

  def bonusFeatsByLevel(level: Int): util.List[String] = {
    val values = displayEnum.values.map((_, level)).collect(filterByClassBonusFeatByLevel)
    values
      .filter(_._2 == level)
      .map(_._1.displayText)
      .sorted
      .asJava
  }

  /**
   * Retrieves Display Text for bonus feats as a Java List. (Use of parenthesis needed when calling
   * from Java (concordion)
   *
   * @return
   *   List of Bonus Feat Names for the specific class sorted in Alpha ascending order.
   */
  def bonusFeats(): util.List[String] = {
    val values = displayEnum.values.collect(filterByClassBonusFeat)
    values.map(_.displayText).sorted.asJava
  }

  def classByLevel(level: Int): util.List[String] =
    classFeatByLevelMap.filter { p =>
      p._2.contains(level)
    }
      .map(_._1)
      .sortWith(_ < _)
      .asJava

  case class Param(e: Entry & ClassRequisite, l: Int)

  case class Param2(e: Entry & MartialArtsFeat, l: Int)

}
