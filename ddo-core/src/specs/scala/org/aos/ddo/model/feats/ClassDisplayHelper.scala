package org.aos.ddo.model.feats

import java.util

import enumeratum.EnumEntry
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.naming.FriendlyDisplay
import org.aos.ddo.support.requisite.{ClassRequisite, Requisite}

import scala.collection.JavaConverters._

/**
  * Created by adarr on 3/5/2017.
  */
trait ClassDisplayHelper extends DisplayHelper {
  //  override type Entry =
  //   EnumEntry with SubFeatInformation with FriendlyDisplay with Requisite
  type FN = (Entry) => Seq[(CharacterClass, Int)]
  val cClass: CharacterClass

  def allOfFilter(as: Entry): Seq[(CharacterClass, Int)] = as match {
    case x: ClassRequisite => x.allOfClass.filter(_._1.eq(cClass))
  }

  def anyOfFilter(as: Entry): Seq[(CharacterClass, Int)] = as match {
    case x: ClassRequisite => x.anyOfClass.filter(_._1.eq(cClass))
  }

  def grantToFilter(as: Entry): Seq[(CharacterClass, Int)] = as match {
    case x: ClassRequisite => x.grantToClass.filter(_._1.eq(cClass))
  }

  def extractLevels(as: Entry, fn: FN*): Seq[(CharacterClass, Int)] =
    fn.flatMap(f => f(as))

  def allTypesFilter(as: Entry): Seq[Int] =
    extractLevels(as, allOfFilter, anyOfFilter, grantToFilter)
      .filter(_._1.eq(cClass))
      .map(_._2)

  lazy val classFeatMap: Seq[(String, Seq[Int])] = enum.values.map { p =>
    p.displayText -> allTypesFilter(p)
  }

  def classByLevel(level: Int): util.List[String] =
    classFeatMap
      .filter { p =>
        p._2.contains(level)
      }
      .map(_._1).sortWith(_ < _)
      .asJava
}
