package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfClass
}

/** Weapon Specialization Passive Feats
  * Icon Feat Weapon Specialization.png
  * Weapon Specialization
  * Passive
  * Provides a +2 bonus to damage rolls with the chosen weapon type and +2 stacking Melee Power or Ranged Power.
  *     This feat can be taken multiple times, once for each weapon type.
  */
protected[feats] trait WeaponSpecializationBase
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with RequiresAllOfClass { self: GeneralFeat =>

  override def allOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Fighter, 4))
}
