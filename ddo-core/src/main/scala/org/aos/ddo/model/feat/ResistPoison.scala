package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Resist Poison.png
  * Resist Poison 	Passive 	Grants a +4 bonus to all saving throws versus Poison.
  * *
  * None
  * */
trait ResistPoison extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
