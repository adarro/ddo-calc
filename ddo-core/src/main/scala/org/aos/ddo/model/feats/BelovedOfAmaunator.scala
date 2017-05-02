package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Amaunator
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

trait BelovedOfAmaunator
    extends FeatRequisiteImpl
    with ForgottenRealmsReligionNonWarforged
    with BelovedLevelBase
    with RequiresAllOfFeat
    with Amaunator
    with AmaunatorFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfAmaunator)
}
