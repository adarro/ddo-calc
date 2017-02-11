package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}


/**
  * Special Attack - Activate this ability to attack one or more enemies in an arc in front of you. This attack deals +1[W] damage.
  * Cleave has a better chance to hit more enemies at once than a basic attack.
  */
protected[feat] trait Cleave extends FeatRequisiteImpl with  Active with RequiresAllOfFeat {
  self: Feat =>
  override val allOfFeats = List(Feat.PowerAttack)
  override lazy val anyOfFeats: Seq[Feat] = IndexedSeq.apply()
  override lazy val noneOfFeats: Seq[Feat] = IndexedSeq.apply()

}
