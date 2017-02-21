package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfRace}

/**
  * Created by adarr on 2/19/2017.
  */
trait Elf extends FeatRequisiteImpl
  with Passive
  with RequiresAllOfRace   { self: RacialFeat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.Elf, 1),(Race.Morninglord,1))
}
