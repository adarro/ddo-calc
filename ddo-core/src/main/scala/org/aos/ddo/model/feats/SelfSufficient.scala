package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Self Sufficient.png
  * Self Sufficient 	Passive 	Provides a +2 bonus to the character's Heal and Repair skills.
  * *
  * None
  */
protected[feats] trait SelfSufficient extends FeatRequisiteImpl with Passive with FreeFeat {
  self: GeneralFeat =>
}
