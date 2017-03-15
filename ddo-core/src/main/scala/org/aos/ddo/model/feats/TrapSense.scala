package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.{Barbarian, Rogue}
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat, GrantsToClass}

/**
  * [[http://ddowiki.com/page/Trap_Sense Trap Sense]]
  * This class feat of Barbarians and Rogues grants the following bonuses:
  * +1 on reflex saving throws vs. traps
  * +1 Armor Class vs. trap attacks.
  */
protected[feats] trait TrapSense
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with FreeFeat { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Barbarian, 3),(Rogue,3))
}
