package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Stealthy.png
  * Stealthy 	Passive 	Provides a +2 bonus to the character's Hide and Move Silently skills.
  */
protected[feats] trait Stealthy extends FeatRequisiteImpl with Passive with FreeFeat {
  self: GeneralFeat =>
}
