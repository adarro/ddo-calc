package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute}

/**
  * [[http://ddowiki.com/page/Power_Attack Power Attack]]
  * This feat exchanges part of your attack bonus for extra melee damage.
  * It reduces your hit bonus by 5, or your Base Attack Bonus, whichever is lower.
  * Then your successful attacks will have their damage increased by the same amount.
  * Two-handed weapons get twice that damage bonus. (Unarmed strikes count as one-handed.)
  * Typically, this means one-handed weapons get +5 and two-handed get +10 to damage.
  *
  * This feat is a stance. It may be toggled on and left active indefinitely.
  * When deactivated, there is a 10 second cooldown before it can be used again.
  *
  * @todo add Cooldown 10 seconds
  * @todo add Offensive Combat Stance
  */
protected[feats] trait PowerAttack extends FeatRequisiteImpl with Active with RequiresAttribute {
  self: GeneralFeat =>
  override val requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Strength, 13))
}
