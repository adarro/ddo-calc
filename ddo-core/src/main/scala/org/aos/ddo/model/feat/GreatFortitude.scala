package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon 	Feat 	Type 	Description 	Prerequisites
  * Icon Feat Great Fortitude.png
  * Great Fortitude 	Passive 	Grants a +2 bonus to Fortitude saves.
  * *
  * None
  */
trait GreatFortitude extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
