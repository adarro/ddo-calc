package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Amaunator
import org.aos.ddo.support.requisite.FeatRequisiteImpl

/**
  * Created by adarr on 4/7/2017.
  */
trait FavoredByAmaunator
    extends FeatRequisiteImpl
    with ForgottenRealmsReligionNonWarforged
    with FollowerBase
    with Amaunator
    with AmaunatorFeatBase { self: DeityFeat =>
}
