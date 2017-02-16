package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * This feat increases the character maximum hit points by +5.
  * Automatically granted at levels 5,10,15
  */
protected[feats] trait ImprovedHeroicDurability extends FeatRequisiteImpl with Passive with FreeFeat {
  self: GeneralFeat =>
}
