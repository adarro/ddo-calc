package org.aos.ddo.model.feats

import java.util
import org.aos.ddo.model.race.Race


import scala.collection.JavaConverters._

/**
  * Created by adarr on 2/19/2017.
  */
trait RaceSupport {
  val raceId: Race

  def verifyRacialFeats: util.List[String] =
    Feat
      .featsFromRace(raceId)
      .map { x =>
        x.displayText
      }
      .toList
      .sorted
      .asJava
}
