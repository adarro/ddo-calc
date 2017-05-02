package org.aos.ddo.model.feats.races

import java.util

import org.aos.ddo.model.feats.{DisplayHelper, Feat, RaceSupport}
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.RequirementOption
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

import scala.collection.JavaConverters._

@RunWith(classOf[ConcordionRunner])
class HalfElfFeatSpec extends DisplayHelper with RaceSupport {

  override val raceId: Race = Race.HalfElf

  def verifyNonDilettante(): util.List[String] = verifyGrantedFeats
//    Feat.featsFromRace(Race.HalfElf)
//      .filter { x =>
//        !x.isSubFeat && !x.entryName.contains("Dilettante")
//      }
//      .map { x =>
//        x.displayText
//      }
//      .toList
//      .sorted
//      .asJava

  def verifyDilettante(): util.List[String] =
    Feat
      .featsFromRace(Race.HalfElf,RequirementOption.Available)
      .filter { x =>
        !x.isSubFeat && x.entryName.contains("Dilettante")
      }
      .map { x =>
        x.displayText
      }
      .toList
      .sorted
      .asJava

  override val enum: E = Feat
}
