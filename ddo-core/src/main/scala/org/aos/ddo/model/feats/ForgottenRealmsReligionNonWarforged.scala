package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.model.race.Race.Warforged
import org.aos.ddo.support.requisite.{RaceRequisiteImpl, RequiresNoneOfRace}

/**
  * Created by adarr on 4/6/2017.
  */
trait ForgottenRealmsReligionNonWarforged
    extends RaceRequisiteImpl
    with RequiresNoneOfRace
    with ForgottenRealmsReligionBase { self: DeityFeat =>
  override def noneOfRace: Seq[(Race, Int)] = List((Warforged, 1))
}
