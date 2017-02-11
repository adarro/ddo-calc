package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Simple Weapon Proficiency.png
  * Simple Weapon Proficiency 	Passive 	This feat negates the penalty from using any of the simple weapons while untrained.
  * *
  * None
  * */
protected[feat] trait SimpleWeaponProficiencyBase extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
