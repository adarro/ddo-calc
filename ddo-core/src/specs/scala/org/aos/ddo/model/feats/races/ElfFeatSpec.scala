package org.aos.ddo.model.feats.races

import org.aos.ddo.model.feats.{ConcordionResourcesBundleSpec, RaceSupport}
import org.aos.ddo.model.race.Race
import org.concordion.api.ConcordionResources
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@ConcordionResources(Array("/images/thumb/*.png", "/images/*.png"))
@RunWith(classOf[ConcordionRunner])
class ElfFeatSpec extends ConcordionResourcesBundleSpec with RaceSupport {
  override val raceId = Race.Elf

}
