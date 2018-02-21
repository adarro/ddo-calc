package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Athletic.png
  * Athletic
  * Passive
  * Provides a +2 bonus to the character's Balance and Swim skills.
  *
  * None
  */
protected[feats] trait Athletic extends FeatRequisiteImpl with Passive with FreeFeat {
  self: GeneralFeat =>
}
