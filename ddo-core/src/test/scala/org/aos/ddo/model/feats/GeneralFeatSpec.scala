package org.aos.ddo.model.feats

import org.concordion.api.FullOGNL
import org.concordion.api.extension.Extensions
import org.concordion.api.option.{ConcordionOptions, MarkdownExtensions}
import org.concordion.ext.EmbedExtension
import org.concordion.ext.collapse.CollapseOutputExtension
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@FullOGNL
@Extensions(Array(classOf[EmbedExtension], classOf[CollapseOutputExtension]))
@RunWith(classOf[ConcordionRunner])
@ConcordionOptions(
  declareNamespaces = Array("ext", "urn:concordion-extensions:2010"),
  markdownExtensions = Array(MarkdownExtensions.WIKILINKS,
                             MarkdownExtensions.AUTOLINKS,
                             MarkdownExtensions.TASKLISTITEMS)
)
class GeneralFeatSpec extends DisplayHelper {
  val enum: E = GeneralFeat

}
