package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * Activate this short-ranged ability while targeting a charmed, commanded, controlled,
  * or dominated enemy that is under your control to dispel the controlling effect.
  */
protected[feats] trait DismissCharm extends FeatRequisiteImpl with Active with FreeFeat {
  self: GeneralFeat =>
}
