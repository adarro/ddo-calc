package org.aos.ddo.model.feats

import org.aos.ddo.support.naming.Prefix
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
  *Activate this ability to lower an animal's or beast's aggression, effectively mesmerizing them.
  *
  * Target: Enemy animals and magical beasts
  * Duration: 5 Min
  * Save: Will
  */
protected[feats] trait WildShape
    extends FeatRequisiteImpl
    with Active
    with Prefix { self: GrantsToClass with RequiresAllOfClass =>

  override def prefix: Option[String] = Some("Wild Shape")

  override protected val prefixSeparator: String = ": "
}
