package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * This feat negates the extra penalties from using a tower shield while untrained. The -2 attack penalty still applies.
  */
protected[feat] trait TowerShieldProficiency extends FeatRequisiteImpl with Passive with RequiresAllOfFeat {
  self: Feat =>
  override val allOfFeats = List(Feat.ShieldProficiency)
}
