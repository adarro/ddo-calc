package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, GrantsToRace}

/**
  * Created by adarr on 2/18/2017.
  */
trait HalfElfKeenSenses
    extends FeatRequisiteImpl
    with Passive
    with GrantsToRace
    with HalfElfPrefix { self: RacialFeat =>
  override def grantsToRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

}
