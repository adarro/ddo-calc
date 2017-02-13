package org.aos.ddo.model.feat

import org.aos.ddo.model.feat.Feat.WeaponSpecialization
import org.aos.ddo.model.item.weapon.{WeaponClass, WeaponClassBludgeoning}
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAnyOfFeat, RequiresBaB}

/** Icon 	Feat 	Type 	Description 	Prerequisites
  * Feat bow strength.png
  * Bow Strength 	Passive 	Strength bonus to damage is applied to Longbow and Shortbow.
  * *
  * Point Blank Shot, Weapon Focus: Ranged Weapons
  * Base Attack Bonus +4
  * MustContainAtLeastOne of: Weapon Specialization: Ranged Weapons, Power Attack, Combat Expertise, Zen Archery
  * */
protected[feat] trait BowStrength extends FeatRequisiteImpl with Passive with RequiresBaB with RequiresAllOfFeat with RequiresAnyOfFeat {
  self: Feat =>
  private def ranged = Feat.weaponSpecializationAny collect { case x: WeaponSpecialization if x.weaponClass == WeaponClass.Ranged => x }

  override def anyOfFeats: Seq[Feat] = List(Feat.PowerAttack, Feat.CombatExpertise, Feat.ZenArchery) ++ ranged

  override def requiresBaB = 4

  override def allOfFeats: Seq[Feat] = ranged :+ Feat.PointBlankShot
}
