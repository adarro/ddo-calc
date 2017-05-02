package org.aos.ddo.model.feats

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.naming.PostText
import org.aos.ddo.support.requisite._

/**
  * Created by adarr on 2/20/2017.
  */
trait LightFortification
    extends FeatRequisiteImpl
    with RaceRequisiteImpl
    with RequiresAttribute
    with GrantsToRace
    with Passive
    with PostText { self: RacialFeat =>
  private val livingConstructs =
    List((Race.Warforged, 1), (Race.Bladeforged, 1))
  override def grantsToRace: Seq[(Race, Int)] = livingConstructs

  override def postText: Option[String] = Some("feat")
}
