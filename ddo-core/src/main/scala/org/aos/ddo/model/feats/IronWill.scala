package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Iron Will.png
  * Iron Will 	Passive 	Grants a +2 bonus to Will saves.
  * *
  * None
  * */
trait IronWill extends FeatRequisiteImpl with Passive with FreeFeat {
  self: GeneralFeat =>
}
