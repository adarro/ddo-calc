package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * This is the standard attack for all characters.
  * It can be toggled on to attack repeatedly until your target is defeated, or activated once by right-clicking the mouse.
  */
protected[feats] trait Attack extends FeatRequisiteImpl with Active with FreeFeat {
  self: GeneralFeat =>
}
