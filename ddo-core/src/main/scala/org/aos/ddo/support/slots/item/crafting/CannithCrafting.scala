package org.aos.ddo.support.slots.item.crafting

import org.aos.ddo.support.slots.item.{AugmentSlot1, AugmentSlot2, Craftable}

/**
  * Cannith Crafting Specific
  */
case class CannithCrafting(prefix: CraftablePrefix,
                           suffix: CraftableSuffix,
                           extra: Option[CraftableExtra],
                           augmentSlot1: Option[AugmentSlot1],
                           augmentSlot2: Option[AugmentSlot2])
    extends Craftable
