package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.SilverFlame
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Created by adarr on 4/7/2017.
  */
trait SilverFlameExorcism
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with DeityUniqueLevelBase
    with RequiresAllOfFeat
    with SilverFlame
    with TheSilverFlameFeatBase
    with Active { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfTheSilverFlame)

}
