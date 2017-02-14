package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAttribute, RequiresBaB}


/** Icon Feat Improved Two Handed Fighting.png
  * Improved Two Handed Fighting 	Passive 	Increases the damage of glancing blow attacks when wielding a two-handed weapon by an additional 10%. Also increases the chance for weapon effects to trigger on glancing blows by an additional 3% (6%) and an additional +2 Combat Style bonus to Melee Power (total of +4).
  *
  * Two Handed Fighting
  * Strength 17
  * Base Attack Bonus +6
  */
trait ImprovedTwoHandedFighting extends FeatRequisiteImpl with Passive with RequiresAllOfFeat with RequiresAttribute with RequiresBaB {
  self: Feat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Strength, 17))

  override def requiresBaB = 6

  override def allOfFeats: Seq[Feat] = List(Feat.TwoHandedFighting)
}