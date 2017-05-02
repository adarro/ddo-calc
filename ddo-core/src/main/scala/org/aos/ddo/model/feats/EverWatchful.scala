package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Helm
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Created by adarr on 4/7/2017.
  */
trait EverWatchful
    extends FeatRequisiteImpl
    with ForgottenRealmsReligionNonWarforged
    with DeityUniqueLevelBase
    with RequiresAllOfFeat
    with Helm
    with HelmFeatBase
    with Active { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfHelm)

}
