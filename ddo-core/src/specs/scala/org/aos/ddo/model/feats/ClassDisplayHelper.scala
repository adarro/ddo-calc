package org.aos.ddo.model.feats

import java.util

import enumeratum.EnumEntry
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.naming.FriendlyDisplay
import org.aos.ddo.support.requisite.{ClassRequisite, Requisite}

import scala.collection.JavaConverters._

/**
  * Created by Adarro on 3/5/2017.
  */
trait ClassDisplayHelper extends DisplayHelper {

  type FNLevel = (Entry) => Seq[(CharacterClass, Int)]
  type FNClass = (Entry) => Seq[CharacterClass]

  val cClass: CharacterClass

  val filterByAllOf: PartialFunction[Entry, Entry] = {
    case x: ClassRequisite if x.allOfClass.exists(isDefinedForClass) => x
  }

  val filterByAnyOf: PartialFunction[Entry, Entry] = {
    case x: ClassRequisite if x.anyOfClass.exists(isDefinedForClass) => x
  }

  val filterByGrantedTo: PartialFunction[Entry, Entry] = {
    case x: ClassRequisite if x.grantToClass.exists(isDefinedForClass) => x
  }

  val existing = filterByAllOf orElse filterByAnyOf orElse filterByGrantedTo

  def isDefinedForClass(e: (CharacterClass, Int)): Boolean = e._1.eq(cClass)

  def allOfFilterByLevel(as: Entry): Seq[(CharacterClass, Int)] = as match {
    case x: ClassRequisite => x.allOfClass.filter(_._1.eq(cClass))
  }

  def anyOfFilterByLevel(as: Entry): Seq[(CharacterClass, Int)] = as match {
    case x: ClassRequisite => x.anyOfClass.filter(_._1.eq(cClass))
  }

  def grantToFilterByLevel(as: Entry): Seq[(CharacterClass, Int)] = as match {
    case x: ClassRequisite => x.grantToClass.filter(_._1.eq(cClass))
  }

  def extractLevels(as: Entry, fn: FNLevel*): Seq[(CharacterClass, Int)] =
    fn.flatMap(f => f(as))

  def allTypesByLevelFilter(as: Entry): Seq[Int] =
    extractLevels(as,
                  allOfFilterByLevel,
                  anyOfFilterByLevel,
                  grantToFilterByLevel)
      .filter(_._1.eq(cClass))
      .map(_._2)

  lazy val classFeatByLevelMap: Seq[(String, Seq[Int])] = {
    val levels = for {
      f <- enum.values
      e <- allTypesByLevelFilter(f)

    } yield f.displayText -> e
    levels
      .groupBy(_._1)
      .map { x =>
        (x._1, x._2.map(_._2))
      }(collection.breakOut)
  }

  lazy val classFeats: util.List[String] = {
    val values = enum.values collect existing
    values.filterNot(_.isSubFeat).map(_.displayText).sorted.asJava
  }

  def classByLevel(level: Int): util.List[String] =
    classFeatByLevelMap
      .filter { p =>
        p._2.contains(level)
      }
      .map(_._1)
      .sortWith(_ < _)
      .asJava
}
