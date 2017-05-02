package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.SilverFlame
import org.aos.ddo.support.requisite.FeatRequisiteImpl

/**
  * Created by adarr on 4/7/2017.
  */
trait FollowerOfTheSilverFlame
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with FollowerBase
    with SilverFlame
    with TheSilverFlameFeatBase { self: DeityFeat =>

}
