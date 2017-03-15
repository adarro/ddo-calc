package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@RunWith(classOf[ConcordionRunner])
class BarbarianClassFeatSpec  extends ClassDisplayHelper {
  override val enum: E = Feat

  override val cClass: CharacterClass = CharacterClass.Barbarian
}
