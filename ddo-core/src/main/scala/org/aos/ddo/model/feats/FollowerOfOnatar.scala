package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Onatar
import org.aos.ddo.support.requisite.FeatRequisiteImpl

/**
  * Created by adarr on 4/7/2017.
  */
trait FollowerOfOnatar
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with FollowerBase
    with Onatar
    with OnatarFeatBase { self: DeityFeat =>

}
