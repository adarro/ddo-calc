package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.FavoredSoul
import org.aos.ddo.model.religions.{Amaunator, SilverFlame, SovereignHost}
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  RequiresAllOfClass,
  RequiresAnyOfFeat
}

import scala.collection.immutable

trait DamageReductionSilver
    extends FeatRequisiteImpl
    with ForgottenRealmsReligionNonWarforged
    with EberronReligionNonWarforged
    with DamageReductionLevelBase
    with RequiresAnyOfFeat
    with RequiresAllOfClass
    with SilverFlame
    with TheSilverFlameFeatBase
    with SovereignHost
    with TheSovereignHostFeatBase
    with Amaunator
    with AmaunatorFeatBase { self: DeityFeat =>

  override def nameSource: String = "Silver"

  override def allOfClass: immutable.Seq[(CharacterClass, Int)] =
    List((FavoredSoul, 20))

  override def anyOfFeats: Seq[Feat] =
    List(DeityFeat.BelovedOfTheSovereignHost,
         DeityFeat.BelovedOfAmaunator,
         DeityFeat.BelovedOfTheSilverFlame)
}
