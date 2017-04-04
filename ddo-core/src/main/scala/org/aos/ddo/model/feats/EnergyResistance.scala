package org.aos.ddo.model.feats

import org.aos.ddo.support.naming.{DisplayName, FriendlyDisplay, Prefix}
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * Created by adarr on 3/5/2017.
  */
trait EnergyResistance extends Prefix with FeatRequisiteImpl with FreeFeat with Passive {
  self:FeatRequisiteImpl with DisplayName with FriendlyDisplay =>
  override def prefix: Option[String] = Some("Energy Resistance")

  /**
    * Delimits the prefix and text.
    */
  override protected val prefixSeparator: String = ": "
}
