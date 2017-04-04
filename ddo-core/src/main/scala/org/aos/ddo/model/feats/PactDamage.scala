package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Warlock
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
  * 1d4 damage from your Eldritch Blast
  */
protected[feats] trait PactDamage
    extends FeatRequisiteImpl
    with Passive
    with RequiresAllOfClass
    with GrantsToClass { self: ClassFeat =>
  private def warlockLevels = 2 to 20 by 2
  override def grantToClass: Seq[(CharacterClass, Int)] =
    warlockLevels.map((Warlock, _))
}
