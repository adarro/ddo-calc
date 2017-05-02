package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Helm
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Created by adarr on 4/7/2017.
  */
trait ChildOfHelm
    extends FeatRequisiteImpl
    with ForgottenRealmsReligionNonWarforged
    with ChildLevelBase
    with RequiresAllOfFeat
    with Helm
    with HelmFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.FavoredByHelm)
}
