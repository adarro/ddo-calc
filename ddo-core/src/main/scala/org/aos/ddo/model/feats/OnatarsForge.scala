package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Onatar
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Created by adarr on 4/7/2017.
  */
trait OnatarsForge
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with DeityUniqueLevelBase
    with RequiresAllOfFeat
    with Onatar
    with OnatarFeatBase
    with Active { self: DeityFeat =>

  override def displayText: String = "Onatar's Forge"

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfOnatar)

}
