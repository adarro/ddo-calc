package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.RequiresCharacterLevel

/**
  * Created by adarr on 4/3/2017.
  */
protected[feats] trait BlindingSpeed extends Passive with RequiresCharacterLevel {
  self: EpicFeat =>
  override val characterLevel: Int = 27
}
