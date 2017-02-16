package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute, RequiresBaB}

/**
  * Icon Feat Weapon Finesse.png
  * Weapon Finesse 	Passive 	This feat allows the character to apply their dexterity modifier to hit instead of the strength modifier when making melee attacks with light weapons and rapiers. The strength modifier is still used as a bonus to the damage roll.
  * *
  * Dexterity 13
  * Base Attack Bonus +1
  * *
  */
trait WeaponFinesse extends FeatRequisiteImpl with Passive with RequiresAttribute with RequiresBaB {
  self: GeneralFeat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 13))

  override def requiresBaB: Int = 1
}
