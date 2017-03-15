package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Barbarian
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  * [[http://ddowiki.com/page/Indomitable_Will Indomitable Will]]
  * While in a Barbarian rage, a barbarian of 14th level or higher gains a +4 bonus on Will saves to resist enchantment spells.
  * This bonus stacks with all other modifiers, including the morale bonus on Will saves he also receives during his rage.
  */
protected[feats] trait IndomitableWill
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with FreeFeat { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Barbarian, 14))
}
