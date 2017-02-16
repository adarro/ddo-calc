package org.aos.ddo.model.feats

import org.aos.ddo.model.feats.GeneralFeat.WeaponSpecialization
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
protected[feats] trait BowStrength extends FeatRequisiteImpl with Passive with RequiresBaB with RequiresAllOfFeat with RequiresAnyOfFeat {
  self: GeneralFeat =>
  private def ranged = GeneralFeat.weaponSpecializationAny collect { case x: WeaponSpecialization if x.weaponClass == WeaponClass.Ranged => x }

  override def anyOfFeats: Seq[GeneralFeat] = List(GeneralFeat.PowerAttack, GeneralFeat.CombatExpertise, GeneralFeat.ZenArchery) ++ ranged

  override def requiresBaB = 4

  override def allOfFeats: Seq[GeneralFeat] = ranged :+ GeneralFeat.PointBlankShot
}
