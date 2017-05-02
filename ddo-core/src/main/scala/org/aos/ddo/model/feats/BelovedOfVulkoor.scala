package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Vulkoor
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

trait BelovedOfVulkoor
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with BelovedLevelBase
    with RequiresAllOfFeat
    with Vulkoor
    with TheVulkoorFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfVulkoor)
}
