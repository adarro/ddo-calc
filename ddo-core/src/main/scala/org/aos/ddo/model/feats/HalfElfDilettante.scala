package org.aos.ddo.model.feats

import org.aos.ddo.support.naming.{DisplayName, FriendlyDisplay, Prefix}

/**
  * Base Trait for Half-Elf Racial Feat family (Half-Elf Dilettante: X)
  */
trait HalfElfDilettante extends Prefix {
  self: DisplayName with FriendlyDisplay =>
  override def prefix: Option[String] = Some("Half-Elf Dilettante")

  /**
    * Delimits the prefix and text.
    */
  override protected val prefixSeparator: String = ": "
}
