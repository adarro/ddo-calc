package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfClass,
  RequiresAllOfFeat
}

/** Icon Feat Greater Weapon Focus.png
  * Greater Weapon Focus 	Passive 	Provides an additional +1 bonus to attack rolls with the chosen weapon type and additional +2 stacking Melee Power or Ranged Power. This feat stacks with Weapon Focus.
  * */
protected[feats] trait GreaterWeaponFocusBase
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with RequiresAllOfClass { self: GeneralFeat with RequiresAllOfFeat =>

  override def allOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Fighter, 8))
}
