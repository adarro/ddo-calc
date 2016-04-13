package org.aos.ddo.weapon

/** Specifies the method of damage delivery.
  */
trait WeaponClass {
  val defaultDeliveryType: WeaponType.Value
}

trait MeleeDamage extends WeaponClass {
  override val defaultDeliveryType = WeaponType.Melee
}

trait RangeDamage extends WeaponClass {
  override val defaultDeliveryType = WeaponType.Ranged
}
trait ThrownDamage extends WeaponClass {
  override val defaultDeliveryType = WeaponType.Thrown
}

/** This trait indicates Damage is delivered in a non-standard way and may require some
  * custom processing.
  */
trait SpecialDamage extends WeaponClass
