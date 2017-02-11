package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Discipline.png
  * Discipline 	Passive 	Provides a +1 on Will saves and +2 to Concentration Skill checks.
  * *
  * None
  */
trait Discipline extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
