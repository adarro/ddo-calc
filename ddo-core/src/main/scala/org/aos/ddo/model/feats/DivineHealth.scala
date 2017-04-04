package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Paladin
import org.aos.ddo.support.requisite._

/**
  * [[http://ddowiki.com/page/Divine_Health]]
  * This class feature makes the Paladin immune to all diseases.
  * As of Update 14, this feat makes the Paladin immune to natural, magical, and supernatural diseases.
  */
protected[feats] trait DivineHealth
    extends FeatRequisiteImpl
    with Passive
    with Active
    with GrantsToClass
    with FreeFeat { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Paladin, 3))

}
