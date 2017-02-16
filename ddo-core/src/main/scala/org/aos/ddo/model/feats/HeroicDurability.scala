package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * This feat increases the character maximum hit points by 30.
  */
protected[feats] trait HeroicDurability extends FeatRequisiteImpl with Passive with FreeFeat {
  self: GeneralFeat =>
}
