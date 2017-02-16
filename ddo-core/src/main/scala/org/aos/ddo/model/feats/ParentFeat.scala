package org.aos.ddo.model.feats

/**
  * Created by adarr on 2/11/2017.
  */
trait ParentFeat {
  self: GeneralFeat =>
  val subFeats: Seq[GeneralFeat with SubFeat]

}
