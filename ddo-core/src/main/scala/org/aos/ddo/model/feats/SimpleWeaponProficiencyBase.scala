package org.aos.ddo.model.feats

import org.aos.ddo.model.item.weapon.SimpleWeapon
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  FreeFeat
}

/** Icon Feat Simple Weapon Proficiency.png
  * Simple Weapon Proficiency
  * Passive
  * This feat negates the penalty from using any of the simple weapons while untrained.
  *
  * None
  */
protected[feats] trait SimpleWeaponProficiencyBase
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with SimpleWeapon
    with Passive
    with FreeFeat
    with WeaponProficiencyBase { self: GeneralFeat =>
}
