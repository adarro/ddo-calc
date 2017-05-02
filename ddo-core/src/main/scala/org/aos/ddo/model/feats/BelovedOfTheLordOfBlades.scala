package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.LordOfBlades
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

trait BelovedOfTheLordOfBlades
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with BelovedLevelBase
    with RequiresAllOfFeat
    with LordOfBlades
    with TheLordOfBladesFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfTheLordOfBlades)
}
