package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfClass,
  RequiresAllOfFeat
}

/**
  * Icon Feat Superior Weapon Focus.png
  * Superior Weapon Focus
  * Passive
  * Provides an additional +1 bonus to attack rolls with the chosen weapon type and additional +2 stacking Melee Power or Ranged Power.
  * @todo add Level 16 Fighter req
  *       Level 16: Fighter, Greater Weapon Focus
  *       Greater Weapon Specialization in the same Specific Weapon Type
  **/
protected[feats] trait SuperiorWeaponFocusBase
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with RequiresAllOfClass { self: GeneralFeat with RequiresAllOfFeat =>

  override def anyOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Fighter, 16))
}
