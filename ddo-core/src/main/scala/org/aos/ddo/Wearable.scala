package org.aos.ddo

/** Indicates the given object can be equipped / wielded etc.
  */
trait Wearable {
  /** A list of allowed location slots
    *
    * Slots are determined by default using the allowedWearLocationFlags bitmask.
    */
  lazy val equipmentSlot = {
    WearLocation.fromMask(allowedWearLocationFlags) match {
      case Some(x) ⇒ x
      case _       ⇒ List[WearLocation with Product with Serializable]()
    }
  }
  /** A bitmask that corresponds to one or more [[org.aos.ddo.WearLocation] values.
    */
  def allowedWearLocationFlags: Int
}
