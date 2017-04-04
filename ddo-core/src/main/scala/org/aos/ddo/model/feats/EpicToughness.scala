package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{RequiresAllOfFeat, RequiresAttribute, RequiresCharacterLevel}

/**
  * Epic Feat that can be taken at level 21 or 24.
  */
protected[feats] trait EpicToughness extends Passive with RequiresCharacterLevel with RequiresAllOfFeat with RequiresAttribute {
  self: EpicFeat =>
  override val characterLevel: Int = 27

  override def allOfFeats: Seq[Feat] = List(GeneralFeat.Toughness)

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Constitution,21))
}
