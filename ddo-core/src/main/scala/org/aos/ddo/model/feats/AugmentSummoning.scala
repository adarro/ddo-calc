package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon 	Feat 	Type 	Description 	Prerequisites
  * Icon Feat Augment Summoning.png
  * Augment Summoning 	Passive 	Your summoned creatures, charmed minions, and hirelings have +4 to all ability scores, increased health, and increased fortification.
  * *
  * None
  * */
protected[feats] trait AugmentSummoning extends FeatRequisiteImpl with Passive with FreeFeat {
  self: GeneralFeat =>
}