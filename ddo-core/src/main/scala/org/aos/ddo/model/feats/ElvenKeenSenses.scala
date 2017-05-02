package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, GrantsToRace}

/**
  * Created by adarr on 2/19/2017.
  */
trait ElvenKeenSenses
    extends FeatRequisiteImpl
    with Passive
    with GrantsToRace { self: RacialFeat =>
  override def grantsToRace: Seq[(Race, Int)] =
    List((Race.Elf, 1), (Race.DrowElf, 1),(Race.Morninglord, 1))
}
