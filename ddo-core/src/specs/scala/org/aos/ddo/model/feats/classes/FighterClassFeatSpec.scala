package org.aos.ddo.model.feats.classes

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Fighter
import org.aos.ddo.model.feats.{ClassDisplayHelper, Feat}
import org.concordion.api.FullOGNL
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@FullOGNL
@RunWith(classOf[ConcordionRunner])
class FighterClassFeatSpec extends ClassDisplayHelper {
  override val cClass: CharacterClass = Fighter
  override val enum: E = Feat
}
