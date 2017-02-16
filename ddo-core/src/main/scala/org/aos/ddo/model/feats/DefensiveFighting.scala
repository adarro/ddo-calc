package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * While using Defensive Fighting mode, you gain a 5% bonus to AC and -5% penalty to-hit.
  * Casting a spell ends this mode.
  */
protected[feats] trait DefensiveFighting extends FeatRequisiteImpl with Active with DefensiveCombatStance with FreeFeat {
  self: GeneralFeat =>
}
