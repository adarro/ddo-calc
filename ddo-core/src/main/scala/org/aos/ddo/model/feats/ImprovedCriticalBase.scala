package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresBaB}

/** Weapon Focus Passive Feats
  *
  * Icon Feat Weapon Focus.png
  * Weapon Focus Passive
  * Provides a +1 bonus to attack rolls with the chosen weapon type Bludgeoning
  *   (includes animal form), Piercing, Ranged, Slashing or Thrown and +2 stacking Melee Power or Ranged Power.)
  *  It can be taken multiple times, once for each of the different types.
  *
  * Base Attack Bonus +1
  * */
trait ImprovedCriticalBase extends FeatRequisiteImpl with Passive with RequiresBaB {

  self: GeneralFeat =>

  override def requiresBaB: Int = 8
}
