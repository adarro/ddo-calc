package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Onatar
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

trait BelovedOfOnatar
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with BelovedLevelBase
    with RequiresAllOfFeat
    with Onatar
    with OnatarFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfOnatar)
}
