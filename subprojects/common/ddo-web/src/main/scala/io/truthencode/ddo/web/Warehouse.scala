/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Warehouse.scala
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
package io.truthencode.ddo.web

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.support.matching.WordMatchStrategy
import io.truthencode.ddo.web.mapping.FieldMapper
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.model._

import scala.collection.mutable.ListBuffer
// import org.jsoup.nodes.{Document, Element}

import scala.collection.mutable
import scala.languageFeature.postfixOps

/**
 * Provides a Storage Area for DDO Items.
 *
 * @author
 *   Andre White
 */
object Warehouse extends LazyLogging {
  private val context = new WebContext()
  lazy val MsgNoReadableLists = "no readable lists detected"
  lazy val MsgErrCantParseField = "could not parse field"

  /**
   * Extracts Multi-values and nested values from an element.
   *
   * @param e
   *   HTML fragment containing desired text
   * @return
   *   a Collection of found elements or an empty list if none are found.
   */
  protected[web] def byExploding(e: Element): List[String] = {
    e >?> elements(HtmlTag.ListItem) match {
      case Some(_) =>
        for ele <- e >> elementList(HtmlTag.ListItem) yield ele.text
      case _ => Nil
    }
  }

  /**
   * Convenience object that holds some HTML extractor patterns.
   */
  object Filter {
    // TODO: Move this to its own file
    /**
     * HTML pattern to determine an initial leaf level.
     *
     * Filters out the .tooltip CSS in a span.
     */
    val FirstLevelLeaf = ":root > li > span:has(:not(.tooltip)) > a"
  }

  /**
   * Attempts to format elements into a list of [[io.truthencode.ddo.web.Leaf]]
   *
   * @param fragment
   *   source HTML fragment
   * @param branchTag
   *   HTML tag for the branch. Defaults to unordered list (ul)
   * @param leafTag
   *   HTML tag for leaf nodes. Defaults to 'List Item' (li)
   * @return
   *   an [[scala.Option]] [[mutable.Buffer]] of [[io.truthencode.ddo.web.Leaf]] of one or more
   *   leaves, or None if none were found.
   */
  def makeLeaves(
    fragment: Element,
    branchTag: String = HtmlTag.UnorderedList,
    leafTag: String = HtmlTag.ListItem): Option[mutable.Buffer[Leaf]] = {
    fragment >?> element(branchTag) match {
      case Some(ele) =>
        val firstLevelItemsWithToolTips = ele.select(Filter.FirstLevelLeaf)
        lazy val msgLeafCount =
          s"Leaf count ${firstLevelItemsWithToolTips.size}"
        logger.info(msgLeafCount)

        val buf = new ListBuffer[Leaf]
        firstLevelItemsWithToolTips.foreach { x =>
          buf.append(Leaf(x.text))
        }
        Some(buf)
      case _ =>
        logger.warn(MsgNoReadableLists)
        None
    }
  }

  /**
   * Extracts text from an html fragment and attempts to format it in a TreeNode like structure.
   *
   * @param fragment
   *   source html
   * @param branchTag
   *   html tag for the branch. Defaults to unordered list (ul)
   * @param leafTag
   *   html tag for leaf nodes. Defaults to 'List Item' (li)
   * @return
   *   an [[io.truthencode.ddo.web.HtmlTreeNode]] populated from the source text.
   */
  def readHtmlList(
    fragment: Element,
    branchTag: String = HtmlTag.UnorderedList,
    leafTag: String = HtmlTag.ListItem): HtmlTreeNode = {
    fragment >?> element(branchTag) match {
      case Some(ele) =>
        val leaves = makeLeaves(ele, branchTag, leafTag)
        val b: Iterable[Leaf] = for
          c: Element <- ele.children
          r: Element <- c >> elementList(s":root > $branchTag ")
          firstLevel <- r >> Filter.FirstLevelLeaf
        yield Leaf(firstLevel)
        val branchedLeaves = if b.nonEmpty then Some(b) else None
        Branch(
          branchedLeaves match {
            case Some(_) => Some(List(Branch(leaves = branchedLeaves)))
            case _ => None
          },
          leaves)
      case _ => Stump()
    }
  }

  /**
   * Loads a web page based on the key
   *
   * @param key
   *   the ID of the item to lookup
   * @param wc
   *   [[WebContext]] used to locate the page
   */
  def loadDoc(key: String, wc: WebContext = new WebContext()): Document = {
    // logger.info(s"url: ${wc.Url(key)}")
    JsoupBrowser().get(wc.url(key))
  }

  /**
   * Scrapes the DDOWiki page and parses the table / list values into a keyed Map
   *
   * @return
   *   Mapped key values which can be passed to helper methods to extract specific values
   */
  def htmlToMappedValues(doc: Document): Option[Map[String, Element]] = {
    // : Option[Map[String, List[String]]]
    // scalastyle:on import.grouping underscore.import
    // TODO: Items with multiple level instances i.e. jeweled cloak with ML12-14 and Epic 23-25
    // need to have alternate search mapping

    // FIXME: Pattern is not exhaustive, multiple level items and non-weapons still need to be tested.
    // logger.info(s"call for ${key} has size ${doc.html().length()}")
    // We need to add the sibling h2 to allow for the update warning template
    doc >?> element("#mw-content-text > h2 + table") match {
      case Some(tables) =>
        val nameRows = tables >> elementList(HtmlTag.TableRow)
        logger.info(s"found ${nameRows.size} name rows")
        val namedRow = nameRows.map { row =>
          val r = row >> element(HtmlTag.TableHeader)
          r.text.trim -> row
        }.toMap
        Some(namedRow)
      case _ => None
    }
  }

  /**
   * Scrapes the DDOWiki page and parses the table / list values into a keyed Map
   *
   * @return
   *   Mapped key values which can be passed to helper methods to extract specific values
   */
  def htmlToMappedValuesOld(doc: Document)(implicit
    strategy: WordMatchStrategy): Option[Map[String, List[String]]] = {

    // scalastyle:on import.grouping underscore.import
    // TODO: Items with multiple level instances i.e. jeweled cloak with ML12-14 and Epic 23-25
    // need to have alternate search mapping

    // logger.info(s"url: ${wc.Url(key)}")
    // logger.info(s"call for ${key} has size ${doc.html().length()}")
    // We need to add the sibling h2 to allow for the update warning template
    doc >?> element("#mw-content-text > h2 + table") match {
      case Some(tables) =>
        val nameRows = tables >> elementList(HtmlTag.TableRow)
        logger.info(s"found ${nameRows.size} name rows")
        val namedRow = nameRows.map { row =>
          (row >> element(HtmlTag.TableHeader)).text.trim -> row
        }.toMap
        val fields = nameRows.map { row =>
          (row >> element(HtmlTag.TableHeader)).text.trim
        }.toSet
        val field = FieldMapper.fieldType(fields)

        val result = field match {
          case Some(fld) =>
            lazy val msgItemType = s"item is of type $fld "
            logger.info(msgItemType)
            val r = FieldMapper.wikiToItem(namedRow)
            None
          case _ =>
            logger.info(MsgErrCantParseField)
            None
        }
        namedRow.foreach { x =>
          logger.info(s"\nnamedRow: ${x._1} data: ${x._2.innerHtml}")
        }
        result
      case _ => None
    }
  }

}
