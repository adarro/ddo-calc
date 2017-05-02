package org.aos.ddo.model.feats

import java.util

import com.typesafe.scalalogging.LazyLogging
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.RequirementOption

import scala.collection.JavaConverters._

/**
  * Created by adarr on 2/19/2017.
  */
trait RaceSupport extends LazyLogging {
  val raceId: Race
  implicit class Util(source: Seq[Feat]) {
    def toJava: util.List[String] = {
      source.map(_.displayText).toList.sorted.asJava
    }
  }

  def verifyGrantedFeats: util.List[String] = {
    val f = Feat
      .featsFromRace(raceId, RequirementOption.AutoGrant)
      .toJava

    logger.info(
      s"verify ${RequirementOption.AutoGrant.entryName} racial feats located ${f
        .size()} values for Race $raceId ")
    logger.debug(s"values: $f")
    f
  }

  def verifyAvailableFeats: util.List[String] = {
    val f = Feat
      .featsFromRace(raceId, RequirementOption.Available)
      .toJava

    logger.info(
      s"verify ${RequirementOption.Available.entryName} racial feats located ${f
        .size()} values for Race $raceId ")
    logger.debug(s"values: $f")
    f
  }
}
