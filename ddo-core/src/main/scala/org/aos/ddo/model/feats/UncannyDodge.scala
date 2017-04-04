package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.{Barbarian, Rogue}
import org.aos.ddo.support.TraverseOps.Crossable
import org.aos.ddo.support.requisite._

/**
  * [[http://ddowiki.com/page/Uncanny_Dodge Uncanny Dodge]]
  * This feat grants you a 1% passive bonus to Dodge at levels 4, 6, 8, 12, 16, and 20.
  * Also, you can activate this ability to gain a temporary 50% dodge bonus and a +6 reflex save bonus.
  * As of Update 14, using this ability is no longer restricted by number of uses per rest. However, it is restricted by cooldown.
  *
  * @note This feat is replaced by [[ImprovedUncannyDodge]] if character has both.
  */
protected[feats] trait UncannyDodge
    extends FeatRequisiteImpl
    with Passive
    with Active
    with GrantsToClass
    with RequiresAnyOfClass { self: ClassFeat =>
  private def rogueAndBarbLevels = (4 to 20 by 4) :+ 6
  private def classMatrix = List(Rogue, Barbarian)
  private lazy val grantedClasses = classMatrix.map((_, 4))
  private def rogueAndBarbMatrix = (classMatrix cross rogueAndBarbLevels).toSeq

  override def grantToClass: Seq[(CharacterClass, Int)] = grantedClasses

  override def anyOfClass: Seq[(CharacterClass, Int)] = grantedClasses
}
