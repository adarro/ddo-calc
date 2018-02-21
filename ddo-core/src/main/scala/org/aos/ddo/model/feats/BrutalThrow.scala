package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute, RequiresBaB}

/** Icon Feat Brutal Throw.png
  * Brutal Throw Passive
  * You can use your Strength bonus instead of Dexterity bonus to determine bonus to attack with Thrown weapons if it is higher.
  *
  * Strength 13
  * Base Attack Bonus +1
  */
trait BrutalThrow extends FeatRequisiteImpl with Passive with RequiresAttribute with RequiresBaB {
  self: GeneralFeat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Strength, 13))

  override def requiresBaB: Int = 1
}

