package org.aos.ddo.model.feats

import org.aos.ddo.support.SearchPrefix

/**
  * Created by adarr on 2/15/2017.
  */
trait FeatSearchPrefix extends SearchPrefix {
  /**
    * Used when qualifying a search with a prefix.
    * Examples include finding "HalfElf" from qualified "Race:HalfElf"
    *
    * @return A default or applied prefix
    */
  override def searchPrefixSource: String = "GeneralFeat"
}
