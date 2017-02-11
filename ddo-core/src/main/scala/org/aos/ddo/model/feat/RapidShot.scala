package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAttribute}

/**
  * Icon Feat Rapid Shot.png
  * Rapid Shot 	Passive 	You can make ranged attacks about 20% faster and reload faster when using a ranged weapon.
  * *
  * Point Blank Shot
  * Dexterity 13,
  */
protected[feat] trait RapidShot extends FeatRequisiteImpl with Passive with RequiresAllOfFeat with RequiresAttribute {
  self: Feat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 13))

  override def allOfFeats: Seq[Feat] = List(Feat.PointBlankShot)
}
