package org.aos.ddo.model.feats

import org.aos.ddo.model.item.weapon.MartialWeapon
import org.aos.ddo.support.requisite.{
  ClassRequisite,
  FeatRequisiteImpl,
  FreeFeat,
  RaceRequisite
}

/** Icon Feat Martial Weapon Proficiency.png
  * Martial Weapon Proficiency Passive
  * This feat negates the penalty from using any of the martial weapons while untrained.
  *
  * Prerequisites None
  */
protected[feats] trait MartialWeaponProficiencyBase
    extends FeatRequisiteImpl
    with RaceRequisite
    with ClassRequisite
    with MartialWeapon
    with Passive
    with FreeFeat { self: GeneralFeat =>
}
