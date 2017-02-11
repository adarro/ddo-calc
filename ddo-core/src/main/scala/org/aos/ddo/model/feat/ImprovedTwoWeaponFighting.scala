package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAttribute, RequiresBaB}

/** Icon Feat Improved Two Weapon Fighting.png
  * Improved Two Weapon Fighting 	Passive 	This feat increases the chance to proc an off-hand attack by 20% (includes unarmed Monk), bringing the total chance to 60%.
  * *
  * Two Weapon Fighting
  * Dexterity 17, Base Attack Bonus +6
  * */
trait ImprovedTwoWeaponFighting extends FeatRequisiteImpl with Passive with RequiresAllOfFeat with RequiresAttribute with RequiresBaB {
  self: Feat =>
  override def requiresBaB = 6

  override def allOfFeats: Seq[Feat] = List(Feat.TwoWeaponFighting)

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 17))
}
