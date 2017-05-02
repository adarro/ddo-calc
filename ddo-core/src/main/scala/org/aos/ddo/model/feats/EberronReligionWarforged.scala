package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.model.race.Race.Warforged
import org.aos.ddo.support.requisite.{RaceRequisiteImpl, RequiresAllOfRace}

/**
  * Created by adarr on 4/6/2017.
  */
trait EberronReligionWarforged
    extends RaceRequisiteImpl
    with RequiresAllOfRace
    with EberronReligionBase { self: DeityFeat =>
  override def allOfRace: Seq[(Race, Int)] = List((Warforged, 1))
}
