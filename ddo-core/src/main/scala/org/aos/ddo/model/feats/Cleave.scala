package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}


/**
  * Special Attack - Activate this ability to attack one or more enemies in an arc in front of you. This attack deals +1[W] damage.
  * Cleave has a better chance to hit more enemies at once than a basic attack.
  */
protected[feats] trait Cleave extends FeatRequisiteImpl with  Active with RequiresAllOfFeat {
  self: GeneralFeat =>
  override val allOfFeats = List(GeneralFeat.PowerAttack)
  override lazy val anyOfFeats: Seq[GeneralFeat] = IndexedSeq.apply()
  override lazy val noneOfFeats: Seq[GeneralFeat] = IndexedSeq.apply()

}
