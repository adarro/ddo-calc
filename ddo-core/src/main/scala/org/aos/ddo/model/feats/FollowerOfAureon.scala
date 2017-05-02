package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.{Aureon, Religion}
import org.aos.ddo.support.requisite.FeatRequisiteImpl

trait FollowerOfAureon
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with FollowerBase
    with Aureon
    with AureonFeatBase { self: DeityFeat =>

}
