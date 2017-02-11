package org.aos.ddo.model.skill

import java.util

import org.concordion.api.FullOGNL
import org.concordion.api.extension.Extensions
import org.concordion.api.option.{ConcordionOptions, MarkdownExtensions}
import org.concordion.ext.EmbedExtension
import org.concordion.ext.collapse.CollapseOutputExtension
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

import scala.collection.JavaConverters._

@FullOGNL
@Extensions(Array(classOf[EmbedExtension], classOf[CollapseOutputExtension]))
@RunWith(classOf[ConcordionRunner])
@ConcordionOptions(declareNamespaces = Array("ext", "urn:concordion-extensions:2010"),
  markdownExtensions = Array(MarkdownExtensions.WIKILINKS, MarkdownExtensions.AUTOLINKS, MarkdownExtensions.TASKLISTITEMS))
class SkillSpec {
  val enum = Skill

  def prettyPrint(): String = {
    listValues("Current Supported Skills", collapse = true)
  }

  def listValues(heading: String, collapse: Boolean = false): String = {

    val data = enum.values.map { a => s"<tr><td>${a.entryName}</td></tr>" }.mkString
    val header = s"<table><tr><th>$heading</th></tr>"
    val footer = "</table>"
    val table = s"$header$data$footer"
    if (collapse) {
      s"""<div class=\"collapsible\">$table</div>"""
    } else {
      table
    }

  }

  def withNameAsList(skillId: String): util.List[Skill] = List(enum.withName(skillId)).asJava


}
