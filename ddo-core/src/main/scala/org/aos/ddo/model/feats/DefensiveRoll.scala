package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{
  ClassRequisite,
  FeatRequisiteImpl,
  GrantsToClass
}

/**
  * If you are at below 20% of your hitpoint maximum,
  *     each time you are struck with an attack there is a percentage chance equal to your Reflex save that the attack does half main damage,
  *     and its special effects are negated (as if you were blocking).
  */
protected[feats] trait DefensiveRoll
    extends FeatRequisiteImpl
    with ClassRequisite
    with Passive
    with GrantsToClass
    with RogueOptionalAbility { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = rogueOptionMatrix

}
