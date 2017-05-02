package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite._

/**
  * Created by adarr on 2/20/2017.
  */
trait BladeforgedFeat
    extends FeatRequisiteImpl
    with RaceRequisiteImpl
    with Passive
    with GrantsToRace { self: RacialFeat =>
  override def grantsToRace: Seq[(Race, Int)] = List((Race.Bladeforged, 1))
}
