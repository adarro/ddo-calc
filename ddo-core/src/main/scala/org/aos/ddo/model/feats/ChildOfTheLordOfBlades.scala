package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.LordOfBlades
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Created by adarr on 4/7/2017.
  */
trait ChildOfTheLordOfBlades
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with ChildLevelBase
    with RequiresAllOfFeat
    with LordOfBlades
    with TheLordOfBladesFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] =
    List(DeityFeat.FollowerOfTheLordOfBlades)
}
