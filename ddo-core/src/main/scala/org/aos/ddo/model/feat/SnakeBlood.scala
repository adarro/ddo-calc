package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Snake Blood.png
  * Snake Blood 	Passive 	Grants a +1 bonus on Reflex saves and +2 bonus to saving throws versus Poison.
  * *
  * None
  */
trait SnakeBlood extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
