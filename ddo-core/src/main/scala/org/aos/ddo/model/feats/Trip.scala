package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * This feat has a chance to trip the target rendering it prone for a short time.
  * Strength or Dexterity save (whichever is higher) is used to oppose a DC of 10 + Strength modifier + related Enhancements + Vertigo.
  * Some creatures may be immune to this effect. Creatures larger or stronger than you are less likely to trip.
  * A successful Balance check negates this effect (DC of 10 + Strength modifier + related Enhancements + Vertigo).
  */
protected[feats] trait Trip extends FeatRequisiteImpl with Active with FreeFeat {
  self: GeneralFeat =>
}
