package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.LordOfBlades
import org.aos.ddo.support.requisite.FeatRequisiteImpl

/**
  * Created by adarr on 4/7/2017.
  */
trait FollowerOfTheLordOfBlades
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with FollowerBase
    with LordOfBlades
    with TheLordOfBladesFeatBase { self: DeityFeat =>

}
