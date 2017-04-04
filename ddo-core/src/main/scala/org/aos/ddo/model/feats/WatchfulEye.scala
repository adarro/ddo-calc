package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.RequiresCharacterLevel

/**
  * If you pass within 5 feet of a trap, you make a Search check to notice it as if actively Searching.
  *
  * @note You still need the Trapfinding feat to successfully Search for difficult traps.
  */
protected[feats] trait WatchfulEye
    extends Passive
    with RequiresCharacterLevel { self: EpicFeat =>
  override val characterLevel: Int = 27
}
