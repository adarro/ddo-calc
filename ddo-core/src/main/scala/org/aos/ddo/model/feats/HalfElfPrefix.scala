package org.aos.ddo.model.feats

import org.aos.ddo.support.naming.{DisplayName, FriendlyDisplay, Prefix}

/**
  * Created by adarr on 2/18/2017.
  */
trait HalfElfPrefix extends Prefix { self: DisplayName with FriendlyDisplay =>
  override def prefix: Option[String] = Some("Half-Elf")

  /**
    * Delimits the prefix and text.
    */
  override protected val prefixSeparator: String = " "
}
