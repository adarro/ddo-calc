package org.aos.ddo.model.feat

import org.aos.ddo.model.item.weapon.MartialWeapon
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Martial Weapon Proficiency.png
  * Martial Weapon Proficiency 	Passive 	This feat negates the penalty from using any of the martial weapons while untrained.
  * *
  * None
  * */
protected[feat] trait MartialWeaponProficiencyBase extends FeatRequisiteImpl with MartialWeapon with Passive with FreeFeat {
  self: Feat =>
}
