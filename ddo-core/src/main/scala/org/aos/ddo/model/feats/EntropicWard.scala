package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Warlock
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass,
  RequiresAllOfFeat
}

/**
  * +5 MRR, +25% Fortification
  */
protected[feats] trait EntropicWard
    extends FeatRequisiteImpl
    with Passive
    with RequiresAllOfClass
    with RequiresAllOfFeat
    with GrantsToClass { self: ClassFeat =>

  override def grantToClass: Seq[(CharacterClass, Int)] = Seq((Warlock, 6))

  override def allOfFeats: Seq[Feat] = Seq(ClassFeat.PactGreatOldOne)
}
