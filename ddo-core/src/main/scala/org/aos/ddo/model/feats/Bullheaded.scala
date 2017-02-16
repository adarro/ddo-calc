package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon 	Feat 	Type 	Description 	Prerequisites
  * Icon Feat Bullheaded.png
  * Bullheaded 	Passive 	Provides a +1 on Will saves and +2 to the Skill Intimidate.
  * *
  * None
  */
trait Bullheaded extends FeatRequisiteImpl with Passive with FreeFeat {
  self: GeneralFeat =>
}
