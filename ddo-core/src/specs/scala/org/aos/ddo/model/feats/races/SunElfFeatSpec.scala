package org.aos.ddo.model.feats.races

import org.aos.ddo.model.feats.RaceSupport
import org.aos.ddo.model.race.Race
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@RunWith(classOf[ConcordionRunner])
class SunElfFeatSpec extends RaceSupport {
  override val raceId: Race = Race.Morninglord
}
