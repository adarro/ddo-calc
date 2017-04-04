package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfClass,
  RequiresAttribute
}

/**
  * Icon Feat Ten Thousand Stars.png
  * Ten Thousand Stars
  * Active - Ability
  * For the next 30 seconds, add your Wisdom ability score to your Ranged Power and add your monk level * 5 to your Doubleshot. This ability puts Manyshot on a 30 second cooldown. Cooldown: one minute.
  * *
  * Level 6: Monk
  * Dexterity 13
  */
protected[feats] trait TenThousandStars
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Active
    with RequiresAttribute
    with RequiresAllOfClass { self: GeneralFeat =>
  override def requiresAttribute: Seq[(Attribute, Int)] =
    List((Attribute.Dexterity, 13))

  override def allOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Monk, 6))
}
