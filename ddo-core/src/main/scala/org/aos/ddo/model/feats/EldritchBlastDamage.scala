package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Warlock
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
  * Eldritch Blast deals 1d6 damage at level 1,
  *     and increases by +1d6 at Warlock level 3, 5, 7, 9, 11, 14, 17, and 20
  *     for a total of 9d6 at level 20. The base damage is Force,
  *     though enhancements can change this to Evil (will affect evil enemies) or Piercing.
  * The base Eldritch Blast scales with 130% spell power, but different shape or essences can change this percentage.
  */
protected[feats] trait EldritchBlastDamage
    extends FeatRequisiteImpl
    with Passive
    with RequiresAllOfClass
    with GrantsToClass { self: ClassFeat =>
  private def artiLevels = List(1, 3, 5, 7, 9, 11, 14, 17, 20)
  override def grantToClass: Seq[(CharacterClass, Int)] =
    artiLevels.map((Warlock, _))
}
