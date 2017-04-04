package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Rogue
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  * [[http://ddowiki.com/page/Sneak_Attack Sneak Attack]]
  * Rogues (or any other character with a source of sneak attack damage) may do additional damage when attacking an opponent
  * if one of the following conditions is met:
  *
  * The target is unaware of the character's presence.
  * The target is attacking another player (possibly under the influence of Intimidate from that player or Diplomacy from the passive character).
  * The target is under the effects of a successful Bluff.
  * The target is helpless.
  * The target is blind.
  * The target is under the effects of a Deception item or attack.
  *
  * @todo Need to apply Rogue Auto Grant
  */
protected[feats] trait SneakAttack
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with FreeFeat { self: ClassFeat =>
  private def rogueLevels = (3 to 19 by 2) :+ 1
  override def grantToClass: Seq[(CharacterClass, Int)] =
    rogueLevels.map((Rogue, _))
}
