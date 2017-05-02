package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Olladra
import org.aos.ddo.support.requisite.FeatRequisiteImpl

/**
  * Created by adarr on 4/7/2017.
  */
trait FollowerOfOlladra
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with FollowerBase
    with Olladra
    with OlladraFeatBase { self: DeityFeat =>

}
