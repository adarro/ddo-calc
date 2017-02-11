package org.aos.ddo.model.feat

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfClass}

/** Icon 	Feat 	Type 	Description 	Prerequisites
  * Icon Heavy Armor Training.png
  * Heavy Armor Training 	Passive 	While in heavy armor, get +3 PRR and MRR.
  *
  * Level 2: Fighter
  * */
trait HeavyArmorTraining extends FeatRequisiteImpl with Passive with RequiresAllOfClass {
  self: Feat =>
  override def allOfClass: Seq[(CharacterClass, Int)] = List((CharacterClass.Fighter, 2))
}
