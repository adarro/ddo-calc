package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Vulkoor
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Created by adarr on 4/7/2017.
  */
trait VulkoorsAvatar
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with DeityUniqueLevelBase
    with RequiresAllOfFeat
    with Vulkoor
    with TheVulkoorFeatBase
    with Active { self: DeityFeat =>

  override def displayText: String = "Vulkoor's Avatar"

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfVulkoor)

}
