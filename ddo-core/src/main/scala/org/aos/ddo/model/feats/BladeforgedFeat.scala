package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  RaceRequisite,
  RequiresAllOfRace
}

/**
  * Created by adarr on 2/20/2017.
  */
trait BladeforgedFeat
    extends FeatRequisiteImpl
    with RaceRequisite
    with Passive
    with RequiresAllOfRace { self: RacialFeat =>
  override def anyOfRace: Seq[(Race, Int)] =
    List((Race.Bladeforged, 1))

  override def grantsToRace: Seq[(Race, Int)] = List((Race.Bladeforged, 1))
}
