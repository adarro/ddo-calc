package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Paladin
import org.aos.ddo.support.requisite._

/**
  * [[http://ddowiki.com/page/Aura_of_Good]]
  * This Paladin class feature grants a +1 bonus to AC and saving throws to all allies within range.
  */
protected[feats] trait AuraOfGood
    extends FeatRequisiteImpl
    with Passive
    with Active
    with GrantsToClass
    with RequiresAllOfClass { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Paladin, 1))

  override def anyOfClass: Seq[(CharacterClass, Int)] = List((Paladin, 1))
}
