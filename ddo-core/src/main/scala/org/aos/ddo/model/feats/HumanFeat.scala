package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  RaceRequisite,
  RequiresAnyOfRace
}
import org.aos.ddo.support.StringUtils.Extensions

/**
  * Created by adarr on 2/20/2017.
  */
trait HumanFeat
    extends FeatRequisiteImpl
    with RaceRequisite
    with Passive
    with RequiresAnyOfRace { self: RacialFeat =>
  override def anyOfRace: Seq[(Race, Int)] =
    List((Race.PurpleDragonKnight, 1), (Race.Shadarkai, 1))

  override def grantsToRace: Seq[(Race, Int)] =
    List((Race.PurpleDragonKnight, 1), (Race.Shadarkai, 1))

  override protected def nameSource: String = "Human".toPascalCase
}
