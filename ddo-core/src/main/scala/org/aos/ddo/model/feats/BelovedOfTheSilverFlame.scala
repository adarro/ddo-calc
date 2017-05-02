package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.SilverFlame
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

trait BelovedOfTheSilverFlame
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with BelovedLevelBase
    with RequiresAllOfFeat
    with SilverFlame
    with TheSilverFlameFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfTheSilverFlame)
}
