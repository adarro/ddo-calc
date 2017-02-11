package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Luck Of Heroes.png
  * Luck of Heroes 	Passive 	Grants a +1 bonus to all saving throws.
  * *
  * None
  * */
trait LuckOfHeroes extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
