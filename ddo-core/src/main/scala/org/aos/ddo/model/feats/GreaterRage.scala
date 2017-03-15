package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Barbarian
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  * [[http://ddowiki.com/page/Greater_Rage Greater Rage]]
  * The bonuses of the barbarian's rage increase to a total of +6 strength,
  * +6 constitution and
  * +3 to will saving throws but the barbarian still incurs a reduction of its Armor Class by 2.
  *
  * Fatigue penalties remain the same as regular Rage. Enhances the regular Barbarian Rage feat. +10 Physical Resistance Rating when wearing Medium armor.
  * +10 Melee Power while the rage is active.
  */
protected[feats] trait GreaterRage
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with FreeFeat { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Barbarian, 11))
}
