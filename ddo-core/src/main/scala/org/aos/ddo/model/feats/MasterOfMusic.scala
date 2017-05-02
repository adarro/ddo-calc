package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.RequiresCharacterLevel

/**
  * @todo add entry levels to allow for 24 or 27 vs minimum.
  */
protected[feats] trait MasterOfMusic
    extends Passive
    with RequiresCharacterLevel { self: EpicFeat =>
  override val characterLevel: Int = 24
}
