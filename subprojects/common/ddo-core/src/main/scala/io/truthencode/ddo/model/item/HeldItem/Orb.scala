package io.truthencode.ddo.model.item.HeldItem

import io.truthencode.ddo.Wearable
import io.truthencode.ddo.model.item.WearableItem
import io.truthencode.ddo.support.slots.WearLocation

trait Orb extends WearableItem with Wearable {

  /**
   * A bitmask that corresponds to one or more [io.truthencode.ddo.WearLocation] values.
   */
  override def allowedWearLocationFlags: Int = WearLocation.OffHand.bitValue
}
