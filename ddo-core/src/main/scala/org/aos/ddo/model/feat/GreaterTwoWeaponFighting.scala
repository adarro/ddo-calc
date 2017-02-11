package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAttribute, RequiresBaB}

/** Icon Feat Greater Two Weapon Fighting.png
  * Greater Two Weapon Fighting 	Passive 	Increases the chance to proc an off-hand attack by 20%, bringing the total chance to 80%.
  * *
  * Improved Two Weapon Fighting
  * Dexterity 17, Base Attack Bonus +11
  * *
  */
trait GreaterTwoWeaponFighting extends FeatRequisiteImpl with Passive with RequiresAllOfFeat with RequiresAttribute with RequiresBaB {
  self: Feat =>
  override def requiresBaB = 11

  override def allOfFeats: Seq[Feat] = List(Feat.ImprovedTwoWeaponFighting)

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 17))
}
