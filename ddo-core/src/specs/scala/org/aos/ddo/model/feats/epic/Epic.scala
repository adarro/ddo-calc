package org.aos.ddo.model.feats.epic

import org.aos.ddo.model.feats.{DisplayHelper, Feat}
import org.concordion.api.FullOGNL
import org.concordion.api.option.{ConcordionOptions, MarkdownExtensions}
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@FullOGNL
@RunWith(classOf[ConcordionRunner])
@ConcordionOptions(
  declareNamespaces = Array("ext", "urn:concordion-extensions:2010"),
  markdownExtensions = Array(MarkdownExtensions.WIKILINKS,
                             MarkdownExtensions.AUTOLINKS,
                             MarkdownExtensions.TASKLISTITEMS)
) class Epic extends DisplayHelper {
  override val enum: E = Feat
}
