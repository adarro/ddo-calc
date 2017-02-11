package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresBaB}

/** Icon Feat Exotic Weapon Proficiency.png
  * Exotic Weapon Proficiency 	Passive 	This feat negates the -4 penalty from using any of the exotic weapons while untrained. Bastard Sword and Dwarven Waraxe deal grazing hits as if they were a two handed weapon if they are the only weapon wielded by a proficient user.
  * This feat must be taken for separate exotic weapons.
  * *
  * Strength 13 for Bastard Sword and Dwarven Waraxe
  * Base Attack Bonus +1,
  */
protected[feat] trait ExoticWeaponProficiency extends FeatRequisiteImpl with Passive with RequiresBaB {
  self: Feat =>
  override def requiresBaB = 1
}
