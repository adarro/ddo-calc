package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Point Blank Shot.png
  * Point Blank Shot 	Passive 	Grants a +1 bonus to hit within 15 meters, and your ranged weapons deal +1[W]. (A weapon that deals 1d6 damage per hit will deal 2d6 damage per hit instead, while a weapon that deals 2d8 damage per hit will deal 4d8 damage per hit instead. This affects the base dice of the associated weapon any time they are rolled, including critical hits.)
  *
  * None
  */
protected[feat] trait PointBlankShot extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
