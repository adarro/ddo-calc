package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Ranger
import org.aos.ddo.support.requisite._

/** Icon Feat Improved Precise Shot.png
  * Improved Precise Shot
  * Active - Ranged Combat Stance
  *
  * Your ranged attacks will now pass through friends and foes to damage all foes in your path, until they strike your intended target.
  *
  * Point Blank Shot, Precise Shot,
  * Dexterity 19, Base Attack Bonus +11
  *
  * @todo add Ranged Combat Stance
  */
protected[feats] trait ImprovedPreciseShot
    extends FeatRequisiteImpl
    with Active
    with RequiresAllOfFeat
    with RequiresAttribute
    with RequiresBaB
    with ClassRequisiteImpl
    with GrantsToClass { self: GeneralFeat =>
  override def allOfFeats: Seq[GeneralFeat] =
    List(GeneralFeat.PointBlankShot, GeneralFeat.PreciseShot)

  /**
    * The Minimum Required Base Attack Bonus
    *
    * @return Minimum value allowed
    */
  override def requiresBaB: Int = 11

  override def requiresAttribute: Seq[(Attribute, Int)] =
    List((Attribute.Dexterity, 19))

  override def grantToClass: Seq[(CharacterClass, Int)] = List((Ranger, 11))
}
