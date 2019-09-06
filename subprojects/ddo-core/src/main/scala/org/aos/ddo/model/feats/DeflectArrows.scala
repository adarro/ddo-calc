package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Monk
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfClass, RequiresAttribute}

trait DeflectArrows extends FeatRequisiteImpl
with RequiresAllOfClass
  with Passive with RequiresAttribute
  with  MartialArtsFeat {
  self: GeneralFeat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 13))

  override def allOfClass: Seq[(CharacterClass, Int)] = List((Monk,1))
}


