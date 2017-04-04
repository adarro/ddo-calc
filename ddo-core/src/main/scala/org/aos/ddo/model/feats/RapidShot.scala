package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Ranger
import org.aos.ddo.support.requisite._

/**
  * Icon Feat Rapid Shot.png
  * Rapid Shot 	Passive 	You can make ranged attacks about 20% faster and reload faster when using a ranged weapon.
  * *
  * Point Blank Shot
  * Dexterity 13,
  * @todo Should we move this to a Class Feat due to the Auto grant to Rangers
  */
protected[feats] trait RapidShot
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with RequiresAllOfFeat
    with RequiresAttribute
    with GrantsToClass { self: GeneralFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Ranger, 2))

  override def requiresAttribute: Seq[(Attribute, Int)] =
    List((Attribute.Dexterity, 13))

  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.PointBlankShot)
}
