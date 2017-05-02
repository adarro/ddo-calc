package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Silvanus
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Created by adarr on 4/7/2017.
  */
trait ChildOfSilvanus
    extends FeatRequisiteImpl
    with ForgottenRealmsReligionNonWarforged
    with ChildLevelBase
    with RequiresAllOfFeat
    with Silvanus
    with SilvanusFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.FavoredBySilvanus)
}
