package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Paladin
import org.aos.ddo.support.requisite._

/**
  * [[http://ddowiki.com/page/Remove_Disease]]
  * At 6th level, a paladin can produce a remove disease effect, as the spell, once per rest period.
  * It can use this ability one additional time per rest period for every three levels after 6th (twice per rest at 9th level).
  */
protected[feats] trait RemoveDisease
    extends FeatRequisiteImpl
    with Passive
    with Active
    with GrantsToClass
    with FreeFeat { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Paladin, 6))

}