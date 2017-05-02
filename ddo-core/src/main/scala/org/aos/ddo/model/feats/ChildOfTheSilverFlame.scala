package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.SilverFlame
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Created by adarr on 4/7/2017.
  */
trait ChildOfTheSilverFlame
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with ChildLevelBase
    with RequiresAllOfFeat
    with SilverFlame
    with TheSilverFlameFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.FollowerOfTheSilverFlame)
}
