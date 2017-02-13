package org.aos.ddo.model.feat

/**
  * Created by adarr on 2/11/2017.
  */
trait ParentFeat {
  self: Feat =>
  val subFeats: Seq[Feat with SubFeat]

}
