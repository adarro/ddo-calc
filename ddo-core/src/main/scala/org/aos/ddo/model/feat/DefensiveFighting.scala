package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * While using Defensive Fighting mode, you gain a 5% bonus to AC and -5% penalty to-hit.
  * Casting a spell ends this mode.
  */
protected[feat] trait DefensiveFighting extends FeatRequisiteImpl with Active with DefensiveCombatStance with FreeFeat {
  self: Feat =>
}
