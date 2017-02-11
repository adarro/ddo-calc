package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/** Icon Feat Hamstring.png
  * Hamstring 	Active - Special Attack 	This melee special attack, when successful, reduces the target's movement rate by half for 12 seconds.
  * Some creatures may be immune to the hamstring effect.
  *
  * Sneak Attack
  */
protected[feat] trait Hamstring extends FeatRequisiteImpl with Active with RequiresAllOfFeat {
  self: Feat =>
  override val allOfFeats = List(Feat.SneakAttack)
}
