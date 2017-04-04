package org.aos.ddo.model.feats.classes

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.feats.{ClassDisplayHelper, Feat}
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@RunWith(classOf[ConcordionRunner])
class ClericClassFeatSpec extends ClassDisplayHelper{
  override val cClass: CharacterClass = CharacterClass.Cleric
  override val enum: E = Feat
}
