package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute}

/** Icon 	Feat 	Type 	Description 	Prerequisites
  * Icon Feat Force Of Personality.png
  * Force of Personality 	Passive 	This Feat allows Player Character to use Charisma modifier for Will saves, instead of Wisdom.
  * *
  * Charisma 13
  * */
trait ForceOfPersonality extends FeatRequisiteImpl with Passive with RequiresAttribute {
  self: Feat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Charisma, 13))
}
