package org.aos.ddo.weapon

/** Base trait to provide a field for weapon proficiency
  */
sealed trait WeaponProficiency {
  /** One of the [[org.aos.ddo.weapon.ProficiencyClass]] values.
    */
  val proficiency: ProficiencyClass.Value
}

/** Type generally requires a fighter like class without special training
  */
trait MartialWeapon extends WeaponProficiency {
  override val proficiency: ProficiencyClass.Value = ProficiencyClass.Martial
}
/** Weapon generally requires no special training to use.
  */
trait SimpleWeapon extends WeaponProficiency {
  override val proficiency: ProficiencyClass.Value = ProficiencyClass.Simple
}
/** Weapon is extremely rare or requires extensive training.
  */
trait ExoticWeapon extends WeaponProficiency {
  override val proficiency: ProficiencyClass.Value = ProficiencyClass.Exotic
}

