package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.UndyingCourt
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

trait BelovedOfTheUndyingCourt
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with BelovedLevelBase
    with RequiresAllOfFeat
    with UndyingCourt
    with TheUndyingCourtFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfTheUndyingCourt)
}
