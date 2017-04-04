package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{
  ClassRequisite,
  FeatRequisiteImpl,
  GrantsToClass
}

/**
  * A Rogue with this ability gains a 3% chance to double strike with melee weapons and bypasses 10% fortification.
  */
protected[feats] trait Opportunist
    extends FeatRequisiteImpl
    with ClassRequisite
    with Passive
    with GrantsToClass
    with RogueOptionalAbility { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = rogueOptionMatrix

}
