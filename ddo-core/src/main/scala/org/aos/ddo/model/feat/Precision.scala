package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute, RequiresBaB}

/** Icon Feat Precision.png
  * Precision 	Active - Offensive Combat Stance 	While using Precision mode, you gain +5% to hit and reduce the target's fortification against your attacks by 25%.
  * Cannot be used while raged.
  * *
  * Dexterity 13
  * Base Attack Bonus +1
  */
protected[feat] trait Precision extends FeatRequisiteImpl with Active with RequiresAttribute with RequiresBaB {
  self: Feat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 13))

  override def requiresBaB = 1
}
