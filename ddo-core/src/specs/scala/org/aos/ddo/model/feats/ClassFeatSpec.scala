package org.aos.ddo.model.feats

import org.concordion.api.option.{ConcordionOptions, MarkdownExtensions}
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@RunWith(classOf[ConcordionRunner])
@ConcordionOptions(
  declareNamespaces = Array("ext", "urn:concordion-extensions:2010"),
  markdownExtensions = Array(MarkdownExtensions.WIKILINKS,
    MarkdownExtensions.AUTOLINKS,
    MarkdownExtensions.TASKLISTITEMS))
class ClassFeatSpec extends DisplayHelper{
  override val enum: E = ClassFeat
}
