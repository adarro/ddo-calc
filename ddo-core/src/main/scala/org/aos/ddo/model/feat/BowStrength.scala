package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAnyOfFeat, RequiresBaB}

/** Icon 	Feat 	Type 	Description 	Prerequisites
  * Feat bow strength.png
  * Bow Strength 	Passive 	Strength bonus to damage is applied to Longbow and Shortbow.
  * *
  * Point Blank Shot, Weapon Focus: Ranged Weapons
  * Base Attack Bonus +4
  * MustContainAtLeastOne of: Weapon Specialization: Ranged Weapons, Power Attack, Combat Expertise, Zen Archery
  * */
trait BowStrength extends FeatRequisiteImpl with Passive with RequiresBaB with RequiresAllOfFeat with RequiresAnyOfFeat {
  self: Feat =>
  override def anyOfFeats: Seq[Feat] = List(Feat.WeaponSpecializationRanged, Feat.PowerAttack, Feat.CombatExpertise, Feat.ZenArchery)

  override def requiresBaB = 4

  override def allOfFeats: Seq[Feat] = List(Feat.PointBlankShot, Feat.WeaponFocusRanged)
}
