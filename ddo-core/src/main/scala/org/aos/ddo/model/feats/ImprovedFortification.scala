package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RaceRequisite, RequiresAnyOfRace, RequiresAttribute, RequiresBaB}

/**
  * Created by adarr on 2/20/2017.
  */
trait ImprovedFortification  extends FeatRequisiteImpl
  with RaceRequisite
  with Passive
  with RequiresAttribute with RequiresBaB
  with RequiresAnyOfRace { self: RacialFeat =>
  override def anyOfRace: Seq[(Race, Int)] =
    List((Race.Warforged, 1), (Race.Bladeforged, 1))

  /**
    * The Minimum Required Base Attack Bonus
    *
    * @return Minimum value allowed
    */
  override def requiresBaB: Int = 6
}