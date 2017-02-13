package org.aos.ddo.model.feat

import org.aos.ddo.model.item.weapon.WeaponClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresBaB}

/** Weapon Focus Passive Feats
  * Icon 	Feat 	Type 	Description 	Prerequisites
  * Icon Feat Weapon Focus.png
  * Weapon Focus 	Passive 	Provides a +1 bonus to attack rolls with the chosen weapon type Bludgeoning (includes animal form), Piercing, Ranged, Slashing or (Thrown) and +2 stacking Melee Power or Ranged Power. It can be taken multiple times, once for each of the different types.
  *
  * Base Attack Bonus +1
  * */
trait WeaponFocusBase extends FeatRequisiteImpl with Passive with RequiresBaB {

  self: Feat =>

  override def requiresBaB: Int = 1
}
