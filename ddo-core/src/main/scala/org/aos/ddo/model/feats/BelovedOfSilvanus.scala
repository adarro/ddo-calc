package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Silvanus
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

trait BelovedOfSilvanus
  extends FeatRequisiteImpl
    with ForgottenRealmsReligionNonWarforged
    with BelovedLevelBase
    with RequiresAllOfFeat
    with Silvanus
    with SilvanusFeatBase {
  self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfSilvanus)
}
