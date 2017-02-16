package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresBaB}

/** Icon Feat Quick Draw.png
  * Quick Draw 	Passive 	Allows the character to switch weapons and armor faster than they would normally be able to. It also increases the rate of fire of thrown weapons, but not of other ranged weapons.
  * *
  * Base Attack Bonus +1
  * */
trait QuickDraw extends FeatRequisiteImpl with Passive with RequiresBaB {
  self: GeneralFeat =>
  override def requiresBaB = 1
}
