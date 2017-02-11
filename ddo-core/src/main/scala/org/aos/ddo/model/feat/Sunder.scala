package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * This melee special attack, when successful, results in a -4 AC penalty to the target for 12 seconds if it fails a Fortitude save (DC 10 + Str mod).
  * Some creatures may be immune to the sunder effect
  */
protected[feat] trait Sunder extends FeatRequisiteImpl with Active with FreeFeat {
  self: Feat =>
}
