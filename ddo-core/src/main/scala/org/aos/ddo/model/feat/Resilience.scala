package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute, RequiresBaB}

/** Icon Feat Resilience.png
  * Resilience 	Active - Defensive Combat Stance 	You gain a +4 to all saving throws. Spells have three times their normal cooldown when this mode is active.
  * *
  * Constitution 13
  * Base Attack Bonus +1
  */
protected[feat] trait Resilience extends FeatRequisiteImpl with Active with RequiresAttribute with RequiresBaB {
  self: Feat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Constitution, 13))

  override def requiresBaB: Int = 1
}
