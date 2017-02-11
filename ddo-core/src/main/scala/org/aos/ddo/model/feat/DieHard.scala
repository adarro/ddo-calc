package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * Icon Feat Diehard.png
  * Diehard
  * Passive
  * You automatically stabilize when incapacitated.
  */
protected[feat] trait DieHard extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
