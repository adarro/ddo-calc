package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**| Icon | Feat | Type | Description | Prerequisites |
 * | --- | --- |
 * | Icon Feat Acrobatic.png | Acrobatic | Passive | Provides a +2 bonus to the character's Jump and Tumble skills.| None |
 */
protected[feats] trait Acrobatic
  extends FeatRequisiteImpl
    with Passive
    with FreeFeat {
  self: GeneralFeat =>
}
