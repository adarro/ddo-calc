package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Helm
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

trait BelovedOfHelm
    extends FeatRequisiteImpl
    with ForgottenRealmsReligionNonWarforged
    with BelovedLevelBase
    with RequiresAllOfFeat
    with Helm
    with HelmFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfHelm)
}
