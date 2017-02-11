package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Stealthy.png
  * Stealthy 	Passive 	Provides a +2 bonus to the character's Hide and Move Silently skills.
  */
protected[feat] trait Stealthy extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
