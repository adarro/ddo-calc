package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/** Icon Feat Shield Mastery.png
  * Shield Mastery 	Passive 	You are skilled with the use of a shield, and your physical resistance rating is increased by 3 when using a buckler or small shield, 5 when using a large shield, or 10 when using a tower shield. You gain 3% chance to double strike while using a shield. Add +3 Melee Power
  * *
  * Shield Proficiency: General
  * *
  */
protected[feats] trait ShieldMastery extends FeatRequisiteImpl with Passive with RequiresAllOfFeat {
  self: GeneralFeat =>
  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.ShieldProficiency)
}
