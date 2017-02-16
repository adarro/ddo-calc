package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * Icon Feat Stunning Blow.png
  * Stunning Blow
  * Active - Special Attack
  * This feat has a chance to stun the target for 6 seconds if it fails a DC (10 + Str mod) Fortitude save.
  * Some creatures may be immune to the stunning effect.
  */
protected[feats] trait StunningBlow extends FeatRequisiteImpl with Active with FreeFeat {
  self: GeneralFeat =>
}
