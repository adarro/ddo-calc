package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite._

/**
  * Created by adarr on 2/20/2017.
  */
trait WarforgedTraits
    extends FeatRequisiteImpl
    with RaceRequisiteImpl
    with Passive
    with RequiresAttribute
    with GrantsToRace { self: RacialFeat =>
  private val wfTraits = List((Race.Warforged, 1), (Race.Bladeforged, 1))

  override def grantsToRace: Seq[(Race, Int)] = wfTraits
}
