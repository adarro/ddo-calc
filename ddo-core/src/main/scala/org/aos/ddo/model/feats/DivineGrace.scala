package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Paladin
import org.aos.ddo.support.requisite._

/**
  * [[http://ddowiki.com/page/Divine_Grace]]
  * At 2nd level, a Paladin gains a bonus equal to his or her Charisma modifier (if not negative) on all saving throws.
  * Divine Grace is capped at 2+(3*paladin level).
  * For multi-classes this means 2 levels of Paladin only grant up to +8 saves.
  */
protected[feats] trait DivineGrace
    extends FeatRequisiteImpl
    with Passive
    with Active
    with GrantsToClass
    with FreeFeat { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Paladin, 2))

}
