package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.FavoredSoul
import org.aos.ddo.model.religions.{SovereignHost, UndyingCourt}
import org.aos.ddo.support.requisite.{
FeatRequisiteImpl,
RequiresAllOfClass,
RequiresAnyOfFeat
}

import scala.collection.immutable

trait DamageReductionColdIron
  extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with DamageReductionLevelBase
    with RequiresAnyOfFeat
    with RequiresAllOfClass
    with UndyingCourt
    with TheUndyingCourtFeatBase
    with SovereignHost
    with TheSovereignHostFeatBase {
  self: DeityFeat =>

  override def nameSource: String = "Cold Iron"

  override def allOfClass: immutable.Seq[(CharacterClass, Int)] =
    List((FavoredSoul, 20))

  override def anyOfFeats: Seq[Feat] =
    List(DeityFeat.BelovedOfTheSovereignHost,
      DeityFeat.BelovedOfTheUndyingCourt)
}
