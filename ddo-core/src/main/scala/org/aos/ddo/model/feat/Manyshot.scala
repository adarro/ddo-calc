package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite._

/**
  * Icon Feat Many Shot.png
  * Manyshot
  * Active - Ability
  * For the next 20 seconds, add your base attack bonus * 4 to your Doubleshot and Ranged power.
  * This ability puts Ten Thousand Stars on a 30 second cooldown. Cooldown: 2 minutes.
  *
  * Point Blank Shot, Rapid Shot
  * Dexterity 17, Base Attack Bonus +6
  *
  * @todo add 30 second Cooldown
  * @todo add 20 second Active
  */
protected[feat] trait Manyshot extends FeatRequisiteImpl with Active with RequiresAllOfFeat with RequiresAttribute with RequiresBaB {
  self: Feat =>
  override def allOfFeats: Seq[Feat] = List(Feat.PointBlankShot, Feat.RapidShot)

  /**
    * The Minimum Required Base Attack Bonus
    *
    * @return Minimum value allowed
    */
  override def requiresBaB: Int = 6

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 17))
}
