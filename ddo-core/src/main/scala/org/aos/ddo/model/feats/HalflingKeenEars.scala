package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToRace,
  RaceRequisiteImpl
}

/**
  * Created by adarr on 2/20/2017.
  */
trait HalflingKeenEars
    extends FeatRequisiteImpl
    with RaceRequisiteImpl
    with Passive
    with GrantsToRace { self: RacialFeat =>
  override def grantsToRace: Seq[(Race, Int)] = List((Race.Halfling, 1))
}
