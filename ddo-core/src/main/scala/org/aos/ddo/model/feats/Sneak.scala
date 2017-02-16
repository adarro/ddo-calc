package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * The character becomes invisible to all enemies that fail a Spot and Listen skill check,
  * opposed by Hide and Move Silently skills.
  */
protected[feats] trait Sneak extends FeatRequisiteImpl with Active with FreeFeat {
  self: GeneralFeat =>
}
