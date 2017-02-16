package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * This feat negates the penalties from using bucklers, and small and Large Shields.
  */
protected[feats] trait ShieldProficiency extends FeatRequisiteImpl with Passive with FreeFeat {
  self: GeneralFeat =>
}
