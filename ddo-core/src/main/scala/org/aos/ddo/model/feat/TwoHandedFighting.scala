package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute}

/**
  * Icon 	Feat 	Type 	Description 	Prerequisites
  * Icon Feat Two Handed Fighting.png
  * Two Handed Fighting 	Passive 	Increases the damage of glancing blow attacks when wielding a two-handed weapon by 10% (from a base of 20% normal weapon damage). Also grants a 3% chance for weapon effects to trigger on glancing blows and a +2 Combat Style bonus to Melee Power.
  * *
  * Strength 15
  **/
trait TwoHandedFighting extends FeatRequisiteImpl with Passive with RequiresAttribute {
  self: Feat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Strength, 15))
}
