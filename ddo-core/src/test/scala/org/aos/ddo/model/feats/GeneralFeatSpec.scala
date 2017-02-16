package org.aos.ddo.model.feats

import java.util

import org.concordion.api.FullOGNL
import org.concordion.api.extension.Extensions
import org.concordion.api.option.{ConcordionOptions, MarkdownExtensions}
import org.concordion.ext.EmbedExtension
import org.concordion.ext.collapse.CollapseOutputExtension
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith
import org.aos.ddo.support.StringUtils.{Extensions => Ext}

import scala.collection.JavaConverters._

@FullOGNL
@Extensions(Array(classOf[EmbedExtension], classOf[CollapseOutputExtension]))
@RunWith(classOf[ConcordionRunner])
@ConcordionOptions(declareNamespaces = Array("ext", "urn:concordion-extensions:2010"),
  markdownExtensions = Array(MarkdownExtensions.WIKILINKS, MarkdownExtensions.AUTOLINKS, MarkdownExtensions.TASKLISTITEMS))
class GeneralFeatSpec {

  val enum = GeneralFeat

  def prettyPrint(): String = {
    listValues("Current Supported Feats", collapse = true)
  }

  def verify(): util.List[String] =
    enum.values
      .filter { x => !x.isSubFeat }
      .map { x => x.displayText }
      .toList.sorted.asJava

  def listValues(heading: String, collapse: Boolean = false): String = {

    val data = enum.values.map { a => s"<tr><td>${a.displayText}</td></tr>" }.sorted.mkString
    val header = s"<table><tr><th>$heading</th></tr>"
    val footer = "</table>"
    val table = s"$header$data$footer"
    if (collapse) {
      s"""<div class=\"collapsible\">$table</div>"""
    } else {
      table
    }
  }

  def withNameAsList(skillId: String): util.List[GeneralFeat] = List(withName(skillId)).asJava

  private def withName(skillId: String) = enum.withName(skillId)
}