package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * This feat negates the penalties from using bucklers, and small and Large Shields.
  */
protected[feat] trait ShieldProficiency extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
