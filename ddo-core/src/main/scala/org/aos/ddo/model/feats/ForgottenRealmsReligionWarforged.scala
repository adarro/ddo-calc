package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.model.race.Race.Warforged
import org.aos.ddo.support.requisite.{
  RaceRequisiteImpl,
  RequiresAllOfRace,
  RequiresNoneOfRace
}

/**
  * Created by adarr on 4/6/2017.
  */
trait ForgottenRealmsReligionWarforged
    extends RaceRequisiteImpl
    with RequiresAllOfRace
    with ForgottenRealmsReligionBase { self: DeityFeat =>
  override def allOfRace: Seq[(Race, Int)] = List((Warforged, 1))
}
