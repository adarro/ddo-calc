package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RaceRequisite, RequiresAllOfRace}
import org.aos.ddo.support.StringUtils.Extensions
import org.aos.ddo.support.naming.Prefix
/**
  * Created by adarr on 2/20/2017.
  */
trait HalfOrcBlood  extends FeatRequisiteImpl
  with RaceRequisite
  with Passive with Prefix
  with RequiresAllOfRace { self: RacialFeat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfOrc, 1))

  /**
    * Delimits the prefix and text.
    */
  override protected val prefixSeparator: String = " "

  override protected def nameSource: String = "Orc Blood".toPascalCase

  override def grantsToRace: Seq[(Race, Int)] = List((Race.HalfOrc, 1))

  override def prefix: Option[String] =  Some("Half-Orc")
}
