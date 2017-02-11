package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAttribute, RequiresBaB}

/** Icon Feat Improved Precise Shot.png
  * Improved Precise Shot 	Active - Ranged Combat Stance 	Your ranged attacks will now pass through friends and foes to damage all foes in your path, until they strike your intended target.
  * *
  * Point Blank Shot, Precise Shot,
  * Dexterity 19, Base Attack Bonus +11
  *
  * @todo add Ranged Combat Stance
  */
protected[feat] trait ImprovedPreciseShot extends FeatRequisiteImpl with Active with RequiresAllOfFeat with RequiresAttribute with RequiresBaB {
  self: Feat =>
  override def allOfFeats: Seq[Feat] = List(Feat.PointBlankShot, Feat.PreciseShot)

  /**
    * The Minimum Required Base Attack Bonus
    *
    * @return Minimum value allowed
    */
  override def requiresBaB: Int = 11

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 19))
}
