package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * This feat increases the character maximum hit points by 30.
  */
protected[feat] trait HeroicDurability extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
