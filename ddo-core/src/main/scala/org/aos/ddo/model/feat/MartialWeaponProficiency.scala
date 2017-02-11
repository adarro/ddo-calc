package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Martial Weapon Proficiency.png
  * Martial Weapon Proficiency 	Passive 	This feat negates the penalty from using any of the martial weapons while untrained. This feat must be taken for separate martial weapons.
  * *
  * None
  * */
protected[feat] trait MartialWeaponProficiency extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
