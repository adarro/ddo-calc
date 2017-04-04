package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfClass
}

/**
  * Icon Heavy Armor Training.png
  * Heavy Armor Training
  * Passive
  * While in heavy armor, get +3 PRR and MRR.
  *
  * Level 2: Fighter
  * */
trait HeavyArmorTraining
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with RequiresAllOfClass { self: GeneralFeat =>
  override def allOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Fighter, 2))
}
