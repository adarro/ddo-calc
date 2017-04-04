package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Paladin
import org.aos.ddo.support.requisite._

/**
  * [[http://ddowiki.com/page/Smite_Evil]]
  * Using this attack, you call upon the paladin's ability to strike down evil creatures,
  *     gaining twice your Charisma bonus and a damage bonus based on your paladin level.
  * Smites return at the rate of one every 90 seconds.
  */
protected[feats] trait SmiteEvil
    extends FeatRequisiteImpl
    with Passive
    with Active
    with GrantsToClass
    with FreeFeat { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Paladin, 1))

}
