package org.aos.ddo.model.feats.epic

import org.aos.ddo.model.feats.{EpicFeat, EpicFeatDisplayHelper, SpellFeats}
import org.concordion.api.FullOGNL
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@FullOGNL
@RunWith(classOf[ConcordionRunner])
class EpicActiveSpellFeatSpec extends EpicFeatDisplayHelper {
  override val filterByCategory: PartialFunction[Entry, EpicFeat] = {
    case x:SpellFeats => x.asInstanceOf[EpicFeat]
  }
}
