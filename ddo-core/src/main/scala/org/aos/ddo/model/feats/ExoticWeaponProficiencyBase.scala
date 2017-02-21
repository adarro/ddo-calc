package org.aos.ddo.model.feats

import org.aos.ddo.support.naming.Prefix
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RaceRequisite, RequiresBaB}

/** Icon Feat Exotic Weapon Proficiency.png
  * Exotic Weapon Proficiency - Passive
  * This feat negates the -4 penalty from using any of the exotic weapons while untrained.
  * Bastard Sword and Dwarven Waraxe deal grazing hits as if they were a two handed weapon if they are the only weapon wielded by a proficient user.
  * This feat must be taken for separate exotic weapons.
  * *
  * Strength 13 for Bastard Sword and Dwarven Waraxe
  * Base Attack Bonus +1,
  */
protected[feats] trait ExoticWeaponProficiencyBase extends FeatRequisiteImpl with RaceRequisite with Prefix with Passive with RequiresBaB {
  self: GeneralFeat =>
  override def requiresBaB: Int = 1

  override def prefix: Option[String] = Some("Exotic Weapon Proficiency")

  /**
    * Delimits the prefix and text.
    */
  override protected val prefixSeparator: String = ": "
}
