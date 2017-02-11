package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAttribute, RequiresBaB}

/** Icon Feat Spring Attack.png
  * Spring Attack
  * Passive
  * Character suffers no penalty to his attack roll when meleeing and moving. You will also gain a 2% dodge bonus.
  *
  * Dodge, Mobility
  * Dexterity 13 , Base Attack Bonus 4, */
protected[feat] trait SpringAttack extends FeatRequisiteImpl with Passive with RequiresAllOfFeat with RequiresAttribute with RequiresBaB {
  self: Feat =>
  override def allOfFeats: Seq[Feat] = List(Feat.Dodge, Feat.Mobility)

  /**
    * The Minimum Required Base Attack Bonus
    *
    * @return Minimum value allowed
    */
  override def requiresBaB: Int = 4

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 13))
}
