package org.aos.ddo.model.feats.epic

import org.aos.ddo.model.feats.{
  EpicFeat,
  EpicFeatDisplayHelper,
  GeneralPassive
}
import org.concordion.api.FullOGNL
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@FullOGNL
@RunWith(classOf[ConcordionRunner])
class EpicGeneralFeatSpec extends EpicFeatDisplayHelper {

  override type C = GeneralPassive
  override val filterByCategory: PartialFunction[Entry, EpicFeat] = {
    case x: GeneralPassive => x.asInstanceOf[EpicFeat]
  }
}
