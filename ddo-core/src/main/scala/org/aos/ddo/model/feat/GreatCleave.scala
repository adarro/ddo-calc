package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite._

/**
  * Great Cleave  Active - Special Attack
  * This feat attacks enemies in a wider arc than Cleave, hence Great Cleave has a greater chance to hit more enemies than Cleave.
  * This attack deals +2[W] damage.
  * Cooldown: 6 seconds
  * Usage: Active, Toggled Stance
  * Prerequisites: Cleave
  * Base Attack Bonus +4
  *
  * @todo Flag Toggled Stance
  *
  */
trait GreatCleave extends  FeatRequisiteImpl with Active with RequiresAllOfFeat with RequiresBaB {
  self: Feat =>
  override val allOfFeats = List(Feat.Cleave)

  /**
    * The Minimum Required Base Attack Bonus
    *
    * @return Minimum value allowed
    */
  override def requiresBaB: Int = 4
}
