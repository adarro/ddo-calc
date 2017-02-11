package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Alertness.png
  * Alertness 	Passive 	Provides a +2 bonus to the character's Listen and Spot skills.
  * *
  * None
  */
protected[feat] trait Alertness extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
