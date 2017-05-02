package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.SovereignHost
import org.aos.ddo.support.requisite.FeatRequisiteImpl

/**
  * Created by adarr on 4/7/2017.
  */
trait FollowerOfTheSovereignHost
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with FollowerBase
    with SovereignHost
    with TheSovereignHostFeatBase { self: DeityFeat =>

}
