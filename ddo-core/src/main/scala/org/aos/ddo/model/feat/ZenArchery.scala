package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute, RequiresBaB}

/** ICON Zen Archery.PNG
  * Zen Archery 	Passive 	You can use your Wisdom bonus instead of Dexterity bonus to determine bonus to attack with ranged missile weapons if it is higher (does not apply to thrown weapons). You treat longbows and shortbows as if they were Ki weapons.
  * *
  * Wisdom 13
  * Base Attack Bonus +1
  * *
  */
trait ZenArchery extends FeatRequisiteImpl with Passive with RequiresAttribute with RequiresBaB {
  self: Feat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Wisdom, 13))

  override def requiresBaB = 1
}
