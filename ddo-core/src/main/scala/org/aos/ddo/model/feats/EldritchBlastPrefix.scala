package org.aos.ddo.model.feats

import org.aos.ddo.support.naming.{DisplayName, FriendlyDisplay, Prefix}

/**
  * Created by adarr on 3/26/2017.
  */
trait EldritchBlastPrefix extends Prefix {
  self: DisplayName with FriendlyDisplay =>
  override def prefix: Option[String] = Some("Eldritch Blast")

  override protected val prefixSeparator: String = ": "
}
