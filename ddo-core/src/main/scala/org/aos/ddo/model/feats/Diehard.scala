package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * Icon Feat Diehard.png
  * Diehard
  * Passive
  * You automatically stabilize when incapacitated.
  */
protected[feats] trait Diehard extends FeatRequisiteImpl with Passive with FreeFeat {
  self: GeneralFeat =>
}
