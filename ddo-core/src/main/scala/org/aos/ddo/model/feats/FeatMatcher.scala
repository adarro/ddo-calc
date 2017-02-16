package org.aos.ddo.model.feats

/**
  * Created by adarr on 2/15/2017.
  */
trait FeatMatcher {

  val matchFeat: PartialFunction[Feat, _ <: Feat]
  val matchFeatById: PartialFunction[String, _ <: Feat]
}
