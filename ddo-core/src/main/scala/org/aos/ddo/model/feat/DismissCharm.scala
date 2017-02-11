package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * Activate this short-ranged ability while targeting a charmed, commanded, controlled,
  * or dominated enemy that is under your control to dispel the controlling effect.
  */
protected[feat] trait DismissCharm extends FeatRequisiteImpl with Active with FreeFeat {
  self: Feat =>
}
