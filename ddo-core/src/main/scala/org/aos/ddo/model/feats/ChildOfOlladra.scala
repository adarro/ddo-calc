package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Olladra
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Created by adarr on 4/7/2017.
  */
trait ChildOfOlladra
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with ChildLevelBase
    with RequiresAllOfFeat
    with Olladra
    with OlladraFeatBase { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.FollowerOfOlladra)
}
