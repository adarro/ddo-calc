package org.aos.ddo.weapon

import org.aos.ddo.{ Enum, NoDefault }

/** used to denote the basic use of the weapon.
  *
  *
  */
sealed trait WeaponType extends WeaponType.Value with NoDefault[WeaponType.Value]
/** Enum to constrain allowed Weapon Types
  */
object WeaponType extends Enum[WeaponType] {
  /** Launches projectiles
    *
    * This includes most bows, crossbows etc.
    */
  case object Ranged extends WeaponType
  /** Close combat
    *
    * Includes swords, daggers, clubs etc
    */
  case object Melee extends WeaponType
  /** Weapons that can be thrown
    *
    * Ninja stars, throwing knives and hammers etc
    */
  case object Thrown extends WeaponType
  /** Weapons that deal damage in multiple or otherwise unique ways.
    */
  case object Special extends WeaponType
  override val values: List[org.aos.ddo.weapon.WeaponType with Product with Serializable] = List(Melee, Ranged, Thrown, Special)
}
