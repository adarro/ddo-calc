package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Helm
import org.aos.ddo.support.requisite.FeatRequisiteImpl

/**
  * Created by adarr on 4/7/2017.
  */
trait FavoredByHelm
    extends FeatRequisiteImpl
    with ForgottenRealmsReligionNonWarforged
    with FollowerBase
    with Helm
    with HelmFeatBase { self: DeityFeat =>

}
