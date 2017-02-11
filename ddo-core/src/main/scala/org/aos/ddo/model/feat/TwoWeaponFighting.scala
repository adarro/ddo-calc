package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute}

/**
  * Icon 	Feat 	Type 	Description 	Prerequisites
  * Icon Feat Two Weapon Fighting.png
  * Two Weapon Fighting 	Passive 	Reduces the to-hit penalty when using two weapons at the same time. The penalty for your primary hand lessens by 2 and the one for your off-hand lessens by 6, resulting in -4/-4 (instead of -6/-10 without this feat). If the off-hand weapon is light, both penalties decrease by another 2 points, down to -2/-2 (instead of -4/-8 without this feat). Two Weapon Fighting also increases the chance to proc an off-hand attack by 20% (applies to unarmed Monk), bringing the total chance to 40%.
  * *
  * Dexterity 15
  **/
trait TwoWeaponFighting extends FeatRequisiteImpl with Passive with RequiresAttribute {
  self: Feat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 15))
}
