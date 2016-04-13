package org.aos.ddo

/** Basic damage for (generally) physical damage
  */
sealed trait PhysicalDamageType extends PhysicalDamageType.Value with NoDefault[PhysicalDamageType.Value]

/** Basic damage enumeration for (generally) physical damage
  */
object PhysicalDamageType extends Enum[PhysicalDamageType] {
  /** Unique damage such as casts spell or other non-basic effect.
    */
  case object Special extends PhysicalDamageType
  /** Blunt force such as delivered by clubs maces and hammers
    */
  case object Bludgeon extends PhysicalDamageType
  /** Stabbing damage such as delivered by Rapiers, arrows, bolts
    */
  case object Pierce extends PhysicalDamageType
  /** Slicing damage such as delivered by Longswords, razors etc
    */
  case object Slash extends PhysicalDamageType
  /** Damage done by Magical means
    */
  case object Magic extends PhysicalDamageType

  val values = List(Bludgeon, Pierce, Slash, Special, Magic)
}
