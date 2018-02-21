package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAttribute}

/** Icon Feat Oversized Two Weapon Fighting.png
  * Oversized Two Weapon Fighting
  * Passive
  * When wielding a one handed weapon in your off-hand, you take penalties for fighting with two weapons as if you were wielding a light weapon on your offhand.
  *
  * Two Weapon Fighting
  * Strength 12
  * */
trait OversizedTwoWeaponFighting extends FeatRequisiteImpl with Passive with RequiresAllOfFeat with RequiresAttribute {
  self: GeneralFeat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Strength, 12))

  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.TwoWeaponFighting)
}
