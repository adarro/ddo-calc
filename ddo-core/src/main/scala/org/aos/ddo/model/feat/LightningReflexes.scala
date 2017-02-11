package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Lightning Reflexes.png
  * Lightning Reflexes 	Passive 	Grants a +2 bonus to Reflex saves.
  * *
  * None
  */
trait LightningReflexes extends FeatRequisiteImpl with Passive with FreeFeat {
  self: Feat =>
}
