package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RaceRequisite, RequiresAnyOfRace}

/**
  * Created by adarr on 2/18/2017.
  */
trait ImmunityToSleep
    extends FeatRequisiteImpl with RaceRequisite
    with Passive
    with RequiresAnyOfRace { self: RacialFeat =>
  override def anyOfRace: Seq[(Race, Int)] =
    List((Race.HalfElf, 1),
         (Race.Elf, 1),
         (Race.DrowElf, 1),
         (Race.Morninglord, 1))

  override def grantsToRace: Seq[(Race, Int)] =  List((Race.HalfElf, 1),
    (Race.Elf, 1),
    (Race.DrowElf, 1),
    (Race.Morninglord, 1))
}
