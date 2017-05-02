package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.BloodOfVol
import org.aos.ddo.support.requisite.FeatRequisiteImpl

/**
  * Created by adarr on 4/7/2017.
  */
trait FollowerOfTheBloodOfVol
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with FollowerBase
    with BloodOfVol
    with TheBloodOfVolFeatBase { self: DeityFeat =>

}
