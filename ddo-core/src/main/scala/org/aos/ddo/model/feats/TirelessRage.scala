package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Barbarian
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  * [[http://ddowiki.com/page/Tireless_Rage Tireless Rage]]
  * Upon reaching level 17, a barbarian is no longer fatigued after raging.
  * Also, gain a stacking +2 Strength and Constitution Bonus along with a +1 Morale Bonus to Will Saving Throws while raging.
  */
protected[feats] trait TirelessRage
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with FreeFeat { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Barbarian, 17))
}
