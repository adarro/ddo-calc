package org.aos.ddo.model.alignment

import java.lang.reflect.InvocationTargetException

import com.typesafe.scalalogging.LazyLogging
import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.support.{ConcordionEnumBuilderHelper, ConcordionEnumBuilderSupport}
import org.concordion.api.FullOGNL
import org.concordion.api.extension.Extensions
import org.concordion.ext.EmbedExtension
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

import scala.util.{Failure, Success, Try}

@RunWith(classOf[ConcordionRunner])
@FullOGNL
@Extensions(Array(classOf[EmbedExtension]))
class LawAxisSpec extends LazyLogging {

  val helper: ConcordionEnumBuilderSupport = new ConcordionEnumBuilderSupport {
    override def actual: Seq[String] = LawAxis.values.map(_.toString)
  }

  def convert(l: LawAxis.type): Enum[_ <: EnumEntry] = {
    l.asInstanceOf[Enum[_ <: EnumEntry]]
  }


  val rows: scala.collection.mutable.SortedSet[String] = scala.collection.mutable.TreeSet.empty

  def setUpRow(value: String): Unit = {
    rows += value
  }

  def getCaseSensitive: String = "Values can be located / limited in a case-sensitive manner"

  def getCaseInSensitive: String = "Values can be located / limited in a case-insensitive manner"

  def getInvalidValues: String = "Invalid values should be handled gracefully"
}
