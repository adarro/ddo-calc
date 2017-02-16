package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * Icon Feat Sap.png
  * Sap
  * Active - Special Attack
  * This feat has a chance to render the target briefly senseless, though it will become active if damaged again.
  * Some creatures may be immune to the sap effect and sap is more effective when performed as a successful sneak attack
  * (whether or not your character can normally perform sneak attacks).
  */
protected trait Sap extends FeatRequisiteImpl with Active with FreeFeat {
  self: GeneralFeat =>
}
