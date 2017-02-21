package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.naming.PostText
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RaceRequisite, RequiresAnyOfRace, RequiresAttribute}

/**
  * Created by adarr on 2/20/2017.
  */
trait LightFortification  extends FeatRequisiteImpl
  with RaceRequisite
  with Passive with PostText
  with RequiresAttribute
  with RequiresAnyOfRace { self: RacialFeat =>
  override def anyOfRace: Seq[(Race, Int)] =
    List((Race.Warforged, 1), (Race.Bladeforged, 1))

  override def postText: Option[String] = Some("Feat")
}