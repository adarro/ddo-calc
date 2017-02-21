package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfRace}

/**
  * Created by adarr on 2/18/2017.
  */
trait HalfElfMixedHeritage extends FeatRequisiteImpl
  with Passive
  with RequiresAllOfRace with HalfElfPrefix {
  self: RacialFeat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

}


