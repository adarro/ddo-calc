package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.FavoredSoul
import org.aos.ddo.model.religions.Vulkoor
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  RequiresAllOfClass,
  RequiresAnyOfFeat
}

import scala.collection.immutable

trait DamageReductionGood
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with DamageReductionLevelBase
    with RequiresAnyOfFeat
    with RequiresAllOfClass
    with Vulkoor
    with TheVulkoorFeatBase { self: DeityFeat =>

  override def nameSource: String = "Good"

  override def allOfClass: immutable.Seq[(CharacterClass, Int)] =
    List((FavoredSoul, 20))

  override def anyOfFeats: Seq[Feat] =
    List(DeityFeat.BelovedOfVulkoor)
}
