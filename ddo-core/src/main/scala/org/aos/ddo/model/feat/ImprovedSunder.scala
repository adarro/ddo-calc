package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Icon Feat Improved Sunder.png
  * Improved Sunder
  * Active - Special Attack
  * This melee special attack results in a -5 AC penalty and -10% fortification to the target for 24 seconds,
  * if the target fails a Fortitude save (DC 14 + Str mod).
  * Whether successful or unsuccessful, this attack will also reduce an enemy's Fortitude saves by 3, stacking up to 5 times.
  * Some creatures may be immune to the sunder effect.
  *
  * Sunder
  * Power Attack
  *
  */
protected[feat] trait ImprovedSunder extends FeatRequisiteImpl with Active with RequiresAllOfFeat {
  self: Feat =>
  override val allOfFeats = List(Feat.Sunder, Feat.PowerAttack)
}
