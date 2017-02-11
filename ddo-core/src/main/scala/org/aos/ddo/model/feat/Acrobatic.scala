package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon 	Feat 	Type 	Description 	Prerequisites
  * Icon Feat Acrobatic.png
  * Acrobatic 	Passive 	Provides a +2 bonus to the character's Jump and Tumble skills.
  * *
  * None
  */
protected[feat] trait Acrobatic extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
