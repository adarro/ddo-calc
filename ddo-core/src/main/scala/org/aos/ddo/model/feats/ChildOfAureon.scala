package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.{Aureon, Religion}
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

trait ChildOfAureon
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with ChildLevelBase
    with RequiresAllOfFeat
    with Aureon
    with AureonFeatBase { self: DeityFeat =>
  override def allOfFeats: Seq[Feat] = List(DeityFeat.FollowerOfAureon)
}
