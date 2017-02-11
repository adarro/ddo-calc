package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Icon Feat Improved Trip.png
  * Improved Trip 	Active - Special Attack 	This feat has a chance to trip the target, if the target fails a Balance check (DC 14 + Str mod), rendering it prone for a longer period of time than Trip. Some creatures may be immune to this effect.
  * *
  * Combat Expertise
  */
protected[feat] trait ImprovedTrip extends FeatRequisiteImpl with Active with RequiresAllOfFeat {
  self: Feat =>
  override val allOfFeats = List(Feat.CombatExpertise)
}
