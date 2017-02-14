package org.aos.ddo.support

import java.lang.Iterable

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.enumeration.EnumExtensions._

import scala.collection.JavaConverters.asJavaIterableConverter
import scala.collection.JavaConverters.asScalaIteratorConverter
import scala.collection.JavaConverters._
import scala.util.Try

/**
  * Created by adarr on 8/14/2016.
  */
class ConcordionEnumBuilderHelper(helper: ConcordionEnumBuilderSupport) {
  def actual: Seq[String] = helper.actual

  implicit private val myStringOrdering: scala.math.Ordering[String] = Ordering.fromLessThan[String](_ > _)

  def listValues(heading: String): String = helper.listValues(heading)

  /**
    * Needed for Concordion / Java compatability as it does not recognize optional parameters.
    *
    * @return
    */
  def listValues(): String = helper.listValues

  def getValidSingleValue: String = helper.getValidSingleValue

  def resultCount(searchString: String, ignoreCase: String): Int = helper.resultCount(searchString, ignoreCase)

  def withNames(searchString: String, ignoreCase: Boolean): Seq[String] = {
    helper.withNames(searchString, ignoreCase = ignoreCase)
  }

}
