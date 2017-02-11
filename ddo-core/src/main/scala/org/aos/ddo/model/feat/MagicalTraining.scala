package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * This feat increases the maximum spell points by 80,
  * increases the spell critical chance by 5% and grants Echoes of Power if the spell points pool drops below 12.
  *
  * @todo granted Level 1 : Cleric, Druid, Favored Soul
  *       Level 1 : Artificer, Sorcerer, Wizard, Warlock
  *       Any other class as a trainable feat
  */
protected[feat] trait MagicalTraining extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
