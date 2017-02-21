package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  RaceRequisite,
  RequiresAllOfRace,
  RequiresAnyOfRace
}

/**
  * Created by adarr on 2/20/2017.
  */
trait SmallSizeBonus
    extends FeatRequisiteImpl
    with RaceRequisite
    with Passive
    with RequiresAnyOfRace { self: RacialFeat =>
  override def anyOfRace: Seq[(Race, Int)] =
    List((Race.Gnome, 1), (Race.DeepGnome, 1), (Race.Halfling, 1))

  override def grantsToRace: Seq[(Race, Int)] =
    List((Race.Gnome, 1), (Race.DeepGnome, 1), (Race.Halfling, 1))

}
