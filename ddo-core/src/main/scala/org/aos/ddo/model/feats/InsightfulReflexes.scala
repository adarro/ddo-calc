package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute}

/** Icon Feat Insightful Reflexes.png
  * Insightful Reflexes 	Passive 	This Feat allows Player Character to use Intelligence modifier for Reflex saves, instead of Dexterity.
  * *
  * Intelligence 13
  */
trait InsightfulReflexes extends FeatRequisiteImpl with Passive with RequiresAttribute {
  self: GeneralFeat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Intelligence, 13))
}
