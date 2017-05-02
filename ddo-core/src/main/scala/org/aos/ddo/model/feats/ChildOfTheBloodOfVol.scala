package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.BloodOfVol
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Created by adarr on 4/7/2017.
  */
trait ChildOfTheBloodOfVol
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with ChildLevelBase
    with RequiresAllOfFeat
    with BloodOfVol
    with TheBloodOfVolFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.FollowerOfTheBloodOfVol)
}
