package org.aos.ddo.support.slots.item.crafting

import org.aos.ddo.support.slots.item.{Craftable, ItemSlot}

/**
  * This slot can be added to a craftable item.
  *
  * Item must be at least ML 10
  */
trait CraftableExtra extends ItemSlot with Craftable
