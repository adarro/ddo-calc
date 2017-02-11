package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * Icon Feat Slicing Blow.png
  * Slicing Blow
  * Active - Special Attack
  * Using this attack, you deal 1 point of Constitution damage to your target and deal 1d4 additional damage 2 seconds later as the target bleeds.
  * The target suffers an additional round of bleeding for every 3 character levels, up to a max of 6 at level 15.
  * Some creatures may be immune to the bleeding effect.
  *
  * None
  */
protected[feat] trait SlicingBlow extends FeatRequisiteImpl with Active with FreeFeat {
  self: Feat =>
}
