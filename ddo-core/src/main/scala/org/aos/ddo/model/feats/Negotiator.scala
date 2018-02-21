package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Negotiator.png
  * Negotiator Passive Provides a +2 bonus to the character's Diplomacy and Haggle skills.
  *
  * None
  * */
protected[feats] trait Negotiator extends FeatRequisiteImpl with Passive with FreeFeat {
  self: GeneralFeat =>
}
