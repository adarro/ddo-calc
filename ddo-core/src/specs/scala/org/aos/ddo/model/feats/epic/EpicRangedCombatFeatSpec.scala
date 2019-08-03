package org.aos.ddo.model.feats.epic

import org.aos.ddo.model.feats.{EpicFeat, EpicFeatDisplayHelper, RangedCombatPassive}
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@RunWith(classOf[ConcordionRunner])
class EpicRangedCombatFeatSpec extends EpicFeatDisplayHelper{

  override val filterByCategory: PartialFunction[Entry, EpicFeat]  = {
    case x: RangedCombatPassive => x.asInstanceOf[EpicFeat]
  }
}
