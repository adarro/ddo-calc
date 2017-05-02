package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.UndyingCourt
import org.aos.ddo.support.requisite.FeatRequisiteImpl

/**
  * Created by adarr on 4/7/2017.
  */
trait FollowerOfTheUndyingCourt
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with FollowerBase
    with UndyingCourt
    with TheUndyingCourtFeatBase { self: DeityFeat =>

}
