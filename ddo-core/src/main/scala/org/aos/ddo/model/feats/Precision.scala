package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute, RequiresBaB}

/** Icon Feat Precision.png
  * Precision 	Active - Offensive Combat Stance 	While using Precision mode, you gain +5% to hit and reduce the target's fortification against your attacks by 25%.
  * Cannot be used while raged.
  * *
  * Dexterity 13
  * Base Attack Bonus +1
  */
protected[feats] trait Precision extends FeatRequisiteImpl with Active with RequiresAttribute with RequiresBaB {
  self: GeneralFeat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 13))

  override def requiresBaB = 1
}
