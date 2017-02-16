package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresBaB}

/** Icon Feat Improved Shield Mastery.png
  * Improved Shield Mastery 	Passive 	You are exceptionally skilled with the use of a shield, and your physical resistance is increased by an additional 5 when using a buckler or small shield, 10 when using a large shield, or 15 when using a tower shield. Your Doublestrike chance while using a shield is increased by 5% to a total of 8%. Add +3 Melee Power(for a total of +6)
  * *
  * Shield Mastery
  * Base Attack Bonus +8
  * */
protected[feats] trait ImprovedShieldMastery extends FeatRequisiteImpl with Passive with RequiresAllOfFeat with RequiresBaB {
  self: GeneralFeat =>
  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.ShieldMastery)

  override def requiresBaB = 8
}
