package org.aos.ddo.model.feats

import org.aos.ddo.support.naming.{DisplayName, FriendlyDisplay, Prefix}

/**
  * Created by adarr on 3/5/2017.
  */
trait DamageReductionPrefix extends Prefix {
  self: DisplayName with FriendlyDisplay =>
  override def prefix: Option[String] = Some("Damage Reduction")

  /**
    * Delimits the prefix and text.
    */
  override protected val prefixSeparator: String = ": "
}
