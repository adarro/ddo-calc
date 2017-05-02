package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FreeFeat, RequiresAllOfFeat, RequiresCharacterLevel}

/**
  * Created by adarr on 4/3/2017.
  */
protected[feats] trait ImprovedAugmentSummoning
    extends FreeFeat
    with SpellCastingPassive
    with RequiresAllOfFeat with RequiresCharacterLevel { self: EpicFeat =>
  override def allOfFeats: Seq[Feat] = List(GeneralFeat.AugmentSummoning)

  /**
    * Default Minimum Level for all Epic Feats.
    * Override this with a higher level as needed.
    */
  override val characterLevel: Int = 24
}
