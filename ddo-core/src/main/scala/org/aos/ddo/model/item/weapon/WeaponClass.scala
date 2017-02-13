package org.aos.ddo.model.item.weapon

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo._
import org.aos.ddo.support.naming.DisplayName
import org.aos.ddo.support.{Deferred, PhysicalDamage}

/**
  * This trait is used to map weapons for purposes such as determining
  * weapon specialization and should correspond directly to such Feats.
  */
sealed trait WeaponClass extends EnumEntry with DisplayName {
  self: DefaultDeliveryMethod with PhysicalDamage =>
  /**
    * Sets or maps the source text for the DisplayName.
    *
    * @return Source text.
    */
  override protected def nameSource: String = entryName
}

object WeaponClass extends Enum[WeaponClass] {

  case object Bludgeon extends WeaponClassBludgeoning

  case object  Piercing extends WeaponClassPiercing

  case object  Slashing extends WeaponClassSlashing

  case object  Ranged extends WeaponClassRanged

  case object  Thrown extends WeaponClassThrown

  override def values: Seq[WeaponClass] = findValues
}

sealed trait WeaponClassBludgeoning extends WeaponClass with MeleeDamage with org.aos.ddo.support.Bludgeoning

sealed trait WeaponClassPiercing extends WeaponClass with MeleeDamage with org.aos.ddo.support.Piercing

sealed trait WeaponClassSlashing extends WeaponClass with MeleeDamage with org.aos.ddo.support.Slashing

sealed trait WeaponClassRanged extends WeaponClass with RangeDamage with Deferred

sealed trait WeaponClassThrown extends WeaponClass with ThrownDamage with Deferred


/*object WeaponClass extends Enum[WeaponClass] {
  case object Bludgeoning extends WeaponClass with MeleeDamage with Bludgeoning
  case object  Piercing extends WeaponClass with MeleeDamage with Piercing
  case class  Ranged () extends WeaponClass with RangeDamage with PhysicalDamage
  case object Slashing extends WeaponClass with MeleeDamage with Slashing
  case object  Thrown extends WeaponClass with RangeDamage with
  override def values: Seq[WeaponClass] = findValues
}*/
