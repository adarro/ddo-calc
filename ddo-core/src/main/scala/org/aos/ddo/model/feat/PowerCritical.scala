package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAnyOfFeat, RequiresBaB}

/** Icon Feat Power Critical.png
  * Power Critical 	Passive 	Provides a +2 bonus to confirm critical hits and +2 bonus to critical hit damage (before multipliers are applied).
  * *
  * Weapon Focus: Any
  * Base Attack Bonus 4
  *
  * */
protected[feat] trait PowerCritical extends FeatRequisiteImpl with Passive with RequiresBaB with RequiresAnyOfFeat {
  self: Feat =>

  override def requiresBaB: Int = 4

  override def anyOfFeats: Seq[Feat] = Feat.WeaponFocusAny
}
