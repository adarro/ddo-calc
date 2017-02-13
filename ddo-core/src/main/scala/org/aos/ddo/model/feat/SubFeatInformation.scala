package org.aos.ddo.model.feat

/**
  * Created by adarr on 2/11/2017.
  */
trait SubFeatInformation {

  /**
    * Flag used for determining if this is a Sub-Feat having a common parent
    *
    * @return
    */
  def isSubFeat: Boolean = false
}
