package org.aos.ddo.model.feats

import java.util

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.ClassRequisite

import scala.collection.JavaConverters._

/**
  * Created by Adarro on 3/5/2017.
  */
trait ClassDisplayHelper extends DisplayHelper {

  type FNLevel = Entry => Seq[(CharacterClass, Int)]
  type FNClass = Entry => Seq[CharacterClass]

  def cClass: CharacterClass

  val filterByAllOf: PartialFunction[Entry, Entry] = {
    case x: ClassRequisite if x.allOfClass.exists(isDefinedForClass(_)) => x
  }

  val filterByAnyOf: PartialFunction[Entry, Entry] = {
    case x: ClassRequisite if x.anyOfClass.exists(isDefinedForClass(_)) => x
  }

  val filterByGrantedTo: PartialFunction[Entry, Entry] = {
    case x: ClassRequisite if x.grantToClass.exists(isDefinedForClass(_)) => x
  }
  case class Param(e: Entry with ClassRequisite, l: Int)

  val filterByGrantedToByLevel: PartialFunction[(Entry, Int), (Entry, Int)] = {
    case (x: ClassRequisite, y)
        if x.grantToClass.exists(isDefinedForClass(_, Some(y))) =>
      (x, y)
  }

  val existing: PartialFunction[Entry, Entry] = filterByAllOf orElse filterByAnyOf orElse filterByGrantedTo

  def isDefinedForClass(e: (CharacterClass, Int),
                        level: Option[Int] = None): Boolean =
    e._1.eq(cClass) && isEqualOrEmpty(e._2, level)

  private def isEqualOrEmpty(i: Int, source: Option[Int]): Boolean =
    source.isEmpty || (source.nonEmpty && source.contains(i))

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

  lazy val grantedFeats: util.List[String] = {
    val values = enum.values collect filterByGrantedTo
    values.filterNot(_.isSubFeat).map(_.displayText).sorted.asJava
  }

  def grantedFeatsByLevel(level: Int): util.List[String] = {
    val values = enum.values.map((_, level)) collect filterByGrantedToByLevel
    values
   //   .filterNot(_._1.isSubFeat)
      .filter(_._2 == level)
      .map(_._1.displayText)
      .sorted
      .asJava
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
