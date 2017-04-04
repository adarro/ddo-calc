package org.aos.ddo.model.feats.classes

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Wizard
import org.aos.ddo.model.feats.{ClassDisplayHelper, Feat}
import org.concordion.api.FullOGNL
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@FullOGNL
@RunWith(classOf[ConcordionRunner])
class WizardClassFeatSpec extends ClassDisplayHelper {
  override val cClass: CharacterClass = Wizard
  override val enum: E = Feat
}
