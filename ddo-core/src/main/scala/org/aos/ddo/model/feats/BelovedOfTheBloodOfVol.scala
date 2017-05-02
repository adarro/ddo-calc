package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.BloodOfVol
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

trait BelovedOfTheBloodOfVol
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with BelovedLevelBase
    with RequiresAllOfFeat
    with BloodOfVol
    with TheBloodOfVolFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfTheBloodOfVol)
}
