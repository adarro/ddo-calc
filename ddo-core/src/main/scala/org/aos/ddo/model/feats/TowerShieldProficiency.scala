package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * This feat negates the extra penalties from using a tower shield while untrained. The -2 attack penalty still applies.
  */
protected[feats] trait TowerShieldProficiency extends FeatRequisiteImpl with Passive with RequiresAllOfFeat {
  self: GeneralFeat =>
  override val allOfFeats = List(GeneralFeat.ShieldProficiency)
}
