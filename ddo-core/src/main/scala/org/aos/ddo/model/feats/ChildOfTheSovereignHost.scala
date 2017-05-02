package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.SovereignHost
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Created by adarr on 4/7/2017.
  */
trait ChildOfTheSovereignHost
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with ChildLevelBase
    with RequiresAllOfFeat
    with SovereignHost
    with TheSovereignHostFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] =
    List(DeityFeat.FollowerOfTheSovereignHost)
}
