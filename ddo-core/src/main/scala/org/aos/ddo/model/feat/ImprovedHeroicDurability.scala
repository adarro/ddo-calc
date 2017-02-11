package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * This feat increases the character maximum hit points by +5.
  * Automatically granted at levels 5,10,15
  */
protected[feat] trait ImprovedHeroicDurability extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
