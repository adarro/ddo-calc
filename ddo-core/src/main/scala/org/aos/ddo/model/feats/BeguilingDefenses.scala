package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Warlock
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
  * +1 to Reflex Saves
  */
protected[feats] trait BeguilingDefenses
    extends FeatRequisiteImpl
    with Passive
    with RequiresAllOfClass
    with GrantsToClass { self: ClassFeat =>

  override def grantToClass: Seq[(CharacterClass, Int)] = List((Warlock, 4))
}
