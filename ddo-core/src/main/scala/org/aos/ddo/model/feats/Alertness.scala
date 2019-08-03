package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** | Icon |  Feat | Description | Prerequisite |
  * | --- | --- | --- | --- |
  * |Alertness.png|| Alertness  | Passive  |	Provides a +2 bonus to the character's Listen and Spot skills. | None |
  */
protected[feats] trait Alertness extends FeatRequisiteImpl with Passive with FreeFeat {
  self: GeneralFeat =>
}
