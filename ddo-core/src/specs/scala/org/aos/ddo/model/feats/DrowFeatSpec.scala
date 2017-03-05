package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@RunWith(classOf[ConcordionRunner])
class DrowFeatSpec extends RaceSupport {
  override val raceId: Race = Race.DrowElf
}
