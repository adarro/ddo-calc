package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Aureon
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

trait BelovedOfAureon
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with BelovedLevelBase
    with RequiresAllOfFeat
    with Aureon
    with AureonFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfAureon)
}
