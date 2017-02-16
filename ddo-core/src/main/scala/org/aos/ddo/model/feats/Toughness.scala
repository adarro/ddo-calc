package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * Icon Feat Toughness.png
  * Toughness 	Passive 	This feat Increases your hit points by +3 at first level, and adds +1 additional hit point for each additional level. This feat can be taken multiple times, so that the effects stack.
  * *
  * None
  **/
protected[feats] trait Toughness extends FeatRequisiteImpl with Passive with FreeFeat {
  self: GeneralFeat =>
}
