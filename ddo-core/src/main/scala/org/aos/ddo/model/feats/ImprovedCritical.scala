package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresBaB}

/**
  * Icon Feat Improved Critical.png
  * Improved Critical 	Passive 	Adds 1, 2, or 3 to critical threat range based on the weapon type's unmodified threat range.This feat is taken for a certain weapon type (Bludgeoning, Piercing, Ranged, Slashing or Thrown), and can be taken multiple times, though each time must be for a different type.
  * *
  * Base Attack Bonus +8
  */
protected[feats] trait ImprovedCritical extends FeatRequisiteImpl with Passive with RequiresBaB {
  self: GeneralFeat =>
  override def requiresBaB: Int = 8
}
