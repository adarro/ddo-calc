package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.{Barbarian, Rogue}
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  * [[http://ddowiki.com/page/Improved_Uncanny_Dodge Improved Uncanny Dodge]]
  * This feat grants you a 1% passive bonus to Dodge at levels 4, 6, 8, 12, 16, and 20.
  * Also, you can activate this ability to gain a temporary 50% dodge bonus and a +6 reflex save bonus.
  * As of Update 14, using this ability is no longer restricted by number of uses per rest. However, it is restricted by cooldown.
  */
protected[feats] trait ImprovedUncannyDodge
    extends FeatRequisiteImpl
    with Passive with Active
    with GrantsToClass
    with FreeFeat { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] =
    List((Barbarian, 8), (Rogue, 8))
}
