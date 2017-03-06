package org.aos.ddo.model.feats

import java.util

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{ClassRequisite, RequiresAnyOfClass}
import org.concordion.api.FullOGNL
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@FullOGNL
@RunWith(classOf[ConcordionRunner])
class ArtificerClassFeatSpec extends ClassDisplayHelper {
  override val enum: E = ClassFeat
  var level = 0
  override val cClass: CharacterClass = CharacterClass.Artificer

}
