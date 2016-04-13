package org.aos.ddo.weapon

import org.aos.ddo.{ Enum, NoDefault }

/** handedness is used to determine a one handed, two handed or off hand equip.
  * DDOwiki lists bows as ranged
  */
sealed trait Handedness extends Handedness.Value with NoDefault[Handedness.Value]
object Handedness extends Enum[Handedness] {
  case object OneHand extends Handedness
  case object TwoHand extends Handedness
  case object OffHand extends Handedness
  override val values: List[org.aos.ddo.weapon.Handedness with Product with Serializable] = List(OneHand, TwoHand, OffHand)
}
