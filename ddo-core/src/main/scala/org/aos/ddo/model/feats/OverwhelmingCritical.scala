package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{RequiresAllOfFeat, RequiresCharacterLevel}

/**
  * @todo Needs to follow parent feat format like weapon focus
  */
protected[feats] trait OverwhelmingCritical extends Passive with RequiresCharacterLevel with RequiresAllOfFeat{
  self: EpicFeat =>
  override val characterLevel: Int = 27

  override def allOfFeats: Seq[Feat] = List(GeneralFeat.ImprovedCritical)
}
