package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Paladin
import org.aos.ddo.support.requisite._

/**
  * [[http://ddowiki.com/page/Lay_on_Hands]]
  * The Paladin ability Lay on Hands heals a friendly target for
  *     ( 10 + Paladin level ) x Charisma modifier amount of hit points,
  *     or deals that much damage to an undead target.
  * The amount healed does not suffer a penalty when used on Warforged (that is, it heals them for 100% of its total).
  * This ability does not require the paladin to be within melee (touch) range of the friend or foe being targeted, but line of sight is required.
  * This Paladin feat is granted at level 2, and may be used once per rest. The Extra Lay on Hands enhancements may be taken at level 1 and above to increase the number of uses per rest.
  *
  * Notes
  * This can also be acquired and enhanced through the Unyielding Sentinel Epic Destiny without requiring any Paladin levels.
  */
protected[feats] trait LayOnHands
    extends FeatRequisiteImpl
    with Passive
    with Active
    with GrantsToClass
    with FreeFeat { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Paladin, 2))

}
