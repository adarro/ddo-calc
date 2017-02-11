package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * This is the standard attack for all characters.
  * It can be toggled on to attack repeatedly until your target is defeated, or activated once by right-clicking the mouse.
  */
protected[feat] trait Attack extends FeatRequisiteImpl with Active with FreeFeat {
  self: Feat =>
}
