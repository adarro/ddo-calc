package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Silvanus
import org.aos.ddo.support.requisite.FeatRequisiteImpl

/**
  * Created by adarr on 4/7/2017.
  */
trait FavoredBySilvanus
    extends FeatRequisiteImpl
    with ForgottenRealmsReligionNonWarforged
    with FollowerBase
    with Silvanus
    with SilvanusFeatBase { self: DeityFeat =>

}
