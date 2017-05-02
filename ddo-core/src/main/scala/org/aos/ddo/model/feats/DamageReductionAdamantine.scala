package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.FavoredSoul
import org.aos.ddo.model.religions.LordOfBlades
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  RequiresAllOfClass,
  RequiresAnyOfFeat
}

import scala.collection.immutable

trait DamageReductionAdamantine
    extends FeatRequisiteImpl
    with EberronReligionWarforged
    with DamageReductionLevelBase
    with RequiresAnyOfFeat
    with RequiresAllOfClass
    with LordOfBlades
    with TheLordOfBladesFeatBase { self: DeityFeat =>

  override def nameSource: String = "Adamantine"

  override def allOfClass: immutable.Seq[(CharacterClass, Int)] =
    List((FavoredSoul, 20))

  override def anyOfFeats: Seq[Feat] =
    List(DeityFeat.BelovedOfTheLordOfBlades)
}
