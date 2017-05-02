package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.BloodOfVol
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Created by adarr on 4/7/2017.
  */
trait TheBloodIsTheLife
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with DeityUniqueLevelBase
    with RequiresAllOfFeat
    with BloodOfVol
    with TheBloodOfVolFeatBase
    with Active { self: DeityFeat =>

  override def displayText: String = "The Blood is The Life"

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfTheBloodOfVol)

}
