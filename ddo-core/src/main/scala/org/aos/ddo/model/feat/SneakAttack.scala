package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

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
protected[feat] trait SneakAttack extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}