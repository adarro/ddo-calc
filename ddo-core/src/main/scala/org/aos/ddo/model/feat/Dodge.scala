package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute}

/**
  *
  * Usage: Passive
  * Prerequisite: Dexterity 13+
  * Description
  * This feat grants a 3% Dodge bonus.
  * *
  *
  * @todo A Fighter may select this feat as one of his fighter bonus feats.
  * @todo A Monk may select this feat as one of his martial arts feats.
  */
trait Dodge extends FeatRequisiteImpl with Passive with RequiresAttribute {
  self: Feat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 13))
}
