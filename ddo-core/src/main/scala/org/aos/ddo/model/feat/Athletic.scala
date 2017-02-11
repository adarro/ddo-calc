package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Athletic.png
  * Athletic 	Passive 	Provides a +2 bonus to the character's Balance and Swim skills.
  * *
  * None
  */
protected[feat] trait Athletic extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
