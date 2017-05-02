package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Vulkoor
import org.aos.ddo.support.requisite.FeatRequisiteImpl

/**
  * Created by adarr on 4/7/2017.
  */
trait FollowerOfVulkoor
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with FollowerBase
    with Vulkoor
    with TheVulkoorFeatBase { self: DeityFeat =>

}
