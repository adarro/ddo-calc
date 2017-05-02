package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite._

/**
  *
  * @todo change this to grant (need to create GrantsToRace trait)
  */
trait CompositePlating
    extends FeatRequisiteImpl
    with RaceRequisiteImpl
    with Passive
    with RequiresAttribute
    with GrantsToRace { self: RacialFeat =>
  private def wfTraits = List((Race.Warforged, 1), (Race.Bladeforged, 1))

  override def grantsToRace: Seq[(Race, Int)] = wfTraits
}
