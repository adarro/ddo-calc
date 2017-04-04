package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfClass
}

/** Icon Heavy Armor Training.png
  * Heavy Armor Champion
  * Passive
  * While in heavy armor, get +12 PRR and MRR.
  *
  * Level 14: Fighter
  * *
  * Note: they all stack with each other.
  *
  */
trait HeavyArmorChampion
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with RequiresAllOfClass { self: GeneralFeat =>
  override def allOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Fighter, 14))
}
