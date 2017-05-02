package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.{Amaunator, Religion}
import org.aos.ddo.support.naming.DisplayProperties
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Created by adarr on 4/7/2017.
  */
trait AmaunatorsFlames
    extends FeatRequisiteImpl
    with ForgottenRealmsReligionNonWarforged
    with DeityUniqueLevelBase
    with RequiresAllOfFeat
    with Amaunator
    with AmaunatorFeatBase
    with Stance
    with DisplayProperties { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfAmaunator)

  override def displayText: String = "Amaunator's Flames"
}
