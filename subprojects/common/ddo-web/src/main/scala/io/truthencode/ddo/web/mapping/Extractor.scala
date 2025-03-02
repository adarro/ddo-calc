/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Extractor.scala
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
package io.truthencode.ddo.web.mapping

import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.model.Element
import io.truthencode.ddo.support.StringUtils.{Comma, EmptyString, Space}
import io.truthencode.ddo.support.dice.DamageInfo

import scala.language.postfixOps
// import org.jsoup.nodes.Element
import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.web.HtmlTag

object Extractor extends LazyLogging {

  /**
   * parser that extracts the text portion from a possibly HTML wrapped string.
   *
   * @param textOrElement
   *   fragment of HTML or Text
   * @param tagSelector
   *   tag to extract. defaults to 'td'
   * @return
   *   extracted text or None if tag was not found or input was not of expected type.
   */
  def simpleExtractor(
    textOrElement: Option[Any],
    tagSelector: String = HtmlTag.TableData): Option[String] = {
    textOrElement match {
      case Some(data: String) =>
        logger.info(msgRawStringData)
        Some(data)
      case Some(data: Element) =>
        val rslt = data >> element(s"$tagSelector:first-child")
        lazy val MsgExtraction = s"returning element extraction $rslt.text"
        logger.info(MsgExtraction)
        Some(rslt.text)
      case Some(data) =>
        logger.warn(
          s"Data was not of expected type (text or Element) - found ${data.getClass.toString}")
        None
      case _ => logger.warn(msgNoData); None
    }

  }

  /**
   * Extracts the critical profile information from a string representation.
   *
   * @param infoText
   *   text containing a min - max x: Multiplier, i.e. 19-20 x3
   * @return
   *   a [[critProfile]] or None if there was no parsable value found.
   */
  def extractCriticalProfile(infoText: String): Some[critProfile] = {

    /**
     * Regular expression used to translate D &amp; D text to object notation.
     */
    val regCritical =
      """(?<min>\d+)?-?(?<max>\d+)\s*/\s*x(?<multiplier>\d+)""".r
    infoText match {
      case regCritical(min, max, multiplier) =>
        logger.info(s"critical profile: $infoText")
        Some(critProfile(Option(min).getOrElse(max).toInt, max.toInt, multiplier.toInt))
      case _ =>
        logger.error(s"argument could not be parsed: $infoText")
        throw new IllegalArgumentException
    }
  }

  /**
   * Helper object to hold extended damage information
   *
   * @param wMod
   *   Weapon Modifier information
   * @param dice
   *   D &amp; D Dice
   * @param extra
   *   Extra damage beyond dice
   * @param damageType
   *   List of Damage types
   */
  case class damageExtractor(wMod: Int, dice: String, extra: Int, damageType: List[String])

  /**
   * Wraps text into an extractor object as an intermediate object to extract additional damage
   * information.
   *
   * @param infoText
   *   structured text read from the Wiki HTML
   * @return
   *   wrapped damageExtractor object with parsed properties.
   */
  def extractDamageInfo(infoText: String): Option[DamageInfo] = {
    val damageInfo =
      """(?<wDamage>\d(?:\.\d+)?)?\s*(?<dice>\[\d+d\d+])(?<damageType>\s\+\s\d+)?\s(.*)""".r
    infoText match {
      case damageInfo(wDamage, dice, extra, damageType) =>
        logger.trace(s"$infoText matched")
        Some(DamageInfo(infoText))
      case _ =>
        logger.warn(s"Could not match infostring to damage extractor $infoText")
        None
    }
  }

  /**
   * Extracts Enchantment data from ddowiki html
   *
   * @param source
   *   wiki scrapped html
   * @return
   *   sequence of possibly nested Leafs containing any enchantments
   */
  def enchantmentExtractor(source: Map[String, Element]): Seq[io.truthencode.ddo.web.Leaf] = {
    val maybeTree =
      for e <- source.get(Field.Enchantments)
      yield io.truthencode.ddo.web.Warehouse.readHtmlList(e)

    maybeTree match {
      case Some(tree) =>
        for
          branches <- tree.branches.toSeq
          branch <- branches
          leaves <- branch.leaves.toSeq
          leaf <- leaves
        yield leaf
      case _ => Nil
    }
  }
}
