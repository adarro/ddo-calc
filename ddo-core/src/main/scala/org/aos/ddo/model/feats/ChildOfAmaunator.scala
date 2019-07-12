package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Amaunator
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Created by adarr on 4/7/2017.
  */
trait ChildOfAmaunator
    extends FeatRequisiteImpl
    with ForgottenRealmsReligionNonWarforged
    with ChildLevelBase
    with RequiresAllOfFeat
    with Amaunator
    with AmaunatorFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.FavoredByAmaunator)
}
