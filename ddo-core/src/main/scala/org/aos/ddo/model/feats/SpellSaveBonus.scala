package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  RaceRequisite,
  RequiresAnyOfRace
}

/**
  * Created by adarr on 2/20/2017.
  */
trait SpellSaveBonus
    extends FeatRequisiteImpl
    with RaceRequisite
    with Passive
    with RequiresAnyOfRace {
  override def anyOfRace: Seq[(Race, Int)] =
    List((Race.DrowElf, 1), (Race.Dwarf, 1))

  override def grantsToRace: Seq[(Race, Int)] =
    List((Race.DrowElf, 1), (Race.Dwarf, 1))
}
