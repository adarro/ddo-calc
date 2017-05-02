package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Onatar
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Created by adarr on 4/7/2017.
  */
trait ChildOfOnatar
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with ChildLevelBase
    with RequiresAllOfFeat
    with Onatar
    with OnatarFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.FollowerOfOnatar)
}
