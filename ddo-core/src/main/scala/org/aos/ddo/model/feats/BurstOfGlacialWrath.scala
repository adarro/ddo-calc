package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FreeFeat, RequiresCharacterLevel}

/**
  * Created by adarr on 4/3/2017.
  */
protected[feats] trait BurstOfGlacialWrath
    extends FreeFeat
    with SpellFeats
    with RequiresCharacterLevel { self: EpicFeat =>

  final override val characterLevel: Int = 24

}
