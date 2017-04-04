package org.aos.ddo.model.feats.classes

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Paladin
import org.aos.ddo.model.feats.{ClassDisplayHelper, Feat}
import org.concordion.api.FullOGNL
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@FullOGNL
@RunWith(classOf[ConcordionRunner])
class PaladinClassFeatSpec extends ClassDisplayHelper {
  override val cClass: CharacterClass = Paladin
  override val enum: E = Feat
}
