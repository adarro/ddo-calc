package org.aos.ddo.model

import org.concordion.api.FullOGNL
import org.concordion.api.extension.Extensions
import org.concordion.api.option.{ConcordionOptions, MarkdownExtensions}
import org.concordion.ext.EmbedExtension
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith


@FullOGNL
@Extensions(Array(classOf[EmbedExtension]))
@RunWith(classOf[ConcordionRunner])
@ConcordionOptions(
  declareNamespaces = Array("ext", "urn:concordion-extensions:2010"),
  markdownExtensions = Array(MarkdownExtensions.WIKILINKS,
                             MarkdownExtensions.AUTOLINKS,
                             MarkdownExtensions.TASKLISTITEMS)
)
class Model {

}
