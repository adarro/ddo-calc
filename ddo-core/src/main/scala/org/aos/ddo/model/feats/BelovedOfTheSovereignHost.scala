package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.SovereignHost
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

trait BelovedOfTheSovereignHost
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with BelovedLevelBase
    with RequiresAllOfFeat
    with SovereignHost
    with TheSovereignHostFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfTheSovereignHost)
}
