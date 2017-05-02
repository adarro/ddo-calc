package org.aos.ddo.model.feats.epic

import org.aos.ddo.model.feats.{
  EpicFeat,
  EpicFeatCategory,
  EpicFeatDisplayHelper,
  EpicMetaMagic
}
import org.concordion.api.FullOGNL
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@FullOGNL
@RunWith(classOf[ConcordionRunner])
class EpicMetamagicFeatSpec extends EpicFeatDisplayHelper {
  override val filterByCategory: PartialFunction[Entry, EpicFeat] = {
    case x: EpicMetaMagic => x.asInstanceOf[EpicFeat]
  }
}
