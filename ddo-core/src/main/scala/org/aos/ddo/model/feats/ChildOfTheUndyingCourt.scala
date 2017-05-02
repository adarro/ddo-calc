package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.UndyingCourt
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Created by adarr on 4/7/2017.
  */
trait ChildOfTheUndyingCourt
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with ChildLevelBase
    with RequiresAllOfFeat
    with UndyingCourt
    with TheUndyingCourtFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] =
    List(DeityFeat.FollowerOfTheUndyingCourt)
}
