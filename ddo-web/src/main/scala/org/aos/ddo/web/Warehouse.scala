/**
  * Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *   http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package org.aos.ddo.web

import scala.collection.mutable.Buffer

import org.aos.ddo.web.mapping.FieldMapper
import org.jsoup.nodes.Element
import org.jsoup.nodes.Document

import com.typesafe.scalalogging.slf4j.LazyLogging

import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.browser.Browser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._

/**
  * Provides a Storage Area for DDO Items.
  * @author Andre White
  */
object Warehouse extends LazyLogging {
  private val context = new WebContext()
  lazy val MsgNoReadableLists = "no readable lists detected"
  lazy val MsgErrCantParseField = "could not parse field"
  /**
    * Extracts Multi-values and nested values from an element.
    * @param e HTML fragment containing desired text
    * @return a Collection of found elements or an empty list if none are found.
    */
  def byExploding(e: Element) = {
    // scalastyle:off import.grouping underscore.import
    import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
    import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
    // scalastyle:on import.grouping underscore.import
    e >?> elements(HtmlTag.ListItem) match {
      case Some(x) => for (ele <- e.getElementsByAttribute(HtmlTag.ListItem)) yield ele.text() // scalastyle:off for.brace
      case _       => Nil
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
    * Attempts to format elements into a list of [[Warehouse.Leaf]]
    *
    * @param fragment source HTML fragment
    * @param branchTag HTML tag for the branch. Defaults to unordered list (ul)
    * @param leafTag HTML tag for leaf nodes. Defaults to 'List Item' (li)
    * @return an [[Option]] [[Buffer]] of [[Warehouse.Leaf]] of one or more
    * leaves, or None if none were found.
    */
  def makeLeaves(fragment: Element, branchTag: String = HtmlTag.UnorderedList, leafTag: String = HtmlTag.ListItem): Option[Buffer[Leaf]] = {
    import net.ruippeixotog.scalascraper.dsl.DSL.Extract._ // scalastyle:off import.grouping underscore.import
    fragment >?> element(branchTag) match {
      case Some(ele) =>
        val firstLevelItemsWithToolTips = ele.select(Filter.FirstLevelLeaf)
        lazy val msgLeafCount = s"Leaf count ${firstLevelItemsWithToolTips.size}"
        logger.info(msgLeafCount)
        val rslt = firstLevelItemsWithToolTips.map { x => new Leaf(x.text()) }
        Some(rslt)
      case _ =>
        logger.warn(MsgNoReadableLists)
        None
    }
  }

  /**
    * Extracts text from an html fragment and attempts to format it in a TreeNode like structure.
    * @param fragment source html
    * @param branchTag html tag for the branch. Defaults to unordered list (ul)
    * @param leafTag html tag for leaf nodes. Defaults to 'List Item' (li)
    * @return an [[org.aos.ddo.web.Warehouse.htmlList]] populated from the source text.
    */
  def readHtmlList(fragment: Element, branchTag: String = HtmlTag.UnorderedList, leafTag: String = HtmlTag.ListItem): HtmlTreeNode = {
    import net.ruippeixotog.scalascraper.dsl.DSL.Extract._ // scalastyle:off import.grouping underscore.import
    fragment >?> element(branchTag) match {
      case Some(ele) => {
        val leaves = makeLeaves(ele, branchTag, leafTag)
        val b = ele.children.select(s":root > ${branchTag} ").select(Filter.FirstLevelLeaf).map { x => logger.info(s"BranchedLeaf ${x.text}"); Leaf(x.text) }
        val branchedLeaves = if (b.size > 0) Some(b) else None
        Branch(branchedLeaves match {
          case Some(x) => { Some(List(Branch(leaves = branchedLeaves))) }
          case _       => { None }
        }, leaves)
      }
      case _ => { Stump() }
    }
  }

  /**
    * Loads a web page based on the key
    * @param key the ID of the item to lookup
    * @param wc [[WebContext]] used to locate the page
    */
  def loadDoc(key: String, wc: WebContext = new WebContext()) = {
    // logger.info(s"url: ${wc.Url(key)}")
    new Browser().get(wc.url(key))
  }

  /**
    * Scrapes the DDOWiki page and parses the table / list values into a keyed Map
    *
    * @return Mapped key values which can be passed to helper methods to extract specific values
    */
  def htmlToMappedValues(doc: Document) = {
    // : Option[Map[String, List[String]]]
    // scalastyle:on import.grouping underscore.import
    // TODO: Items with multiple level instances i.e. jeweled cloak with ML12-14 and Epic 23-25
    // need to have alternate search mapping

    // FIXME: Pattern is not exhaustive, multiple level items and non-weapons still need to be tested.
    // logger.info(s"call for ${key} has size ${doc.html().length()}")
    // We need to add the sibling h2 to allow for the update warning template
    doc >?> element("#mw-content-text > h2 + table") match {
      case Some(tables) =>
        val nameRows = tables.getElementsByTag(HtmlTag.TableRow)
        logger.info(s"found ${nameRows.size()} name rows")
        val namedRow = nameRows.map { (row => row.select(HtmlTag.TableHeader).text.trim() -> row) }.toMap
        Some(namedRow)
      case _ => None
    }
  }

  /**
    * Scrapes the DDOWiki page and parses the table / list values into a keyed Map
    *
    * @return Mapped key values which can be passed to helper methods to extract specific values
    */
  def htmlToMappedValuesOld(doc: Document): Option[Map[String, List[String]]] = {

    // scalastyle:on import.grouping underscore.import
    // TODO: Items with multiple level instances i.e. jeweled cloak with ML12-14 and Epic 23-25
    // need to have alternate search mapping

    // logger.info(s"url: ${wc.Url(key)}")
    // logger.info(s"call for ${key} has size ${doc.html().length()}")
    // We need to add the sibling h2 to allow for the update warning template
    doc >?> element("#mw-content-text > h2 + table") match {
      case Some(tables) => {
        val nameRows = tables.getElementsByTag(HtmlTag.TableRow)
        logger.info(s"found ${nameRows.size()} name rows")
        val namedRow = nameRows.map { (row => row.select(HtmlTag.TableHeader).text.trim() -> row) }.toMap
        val fields = nameRows.map { row => row.select(HtmlTag.TableHeader).text.trim() }.toSet
        val field = FieldMapper.fieldType(fields)

        val result = field match {
          case Some(field) =>
            lazy val msgItemType = s"item is of type ${field} "
            logger.info(msgItemType);
            val r = FieldMapper.wikiToItem(namedRow)
            None
          case _ =>
            logger.info(MsgErrCantParseField);
            None
        }
        namedRow.foreach { x => logger.info(s"\nnamedRow: ${x._1} data: ${x._2.html()}") }
        result
      }
      case _ => None
    }
  }

}
