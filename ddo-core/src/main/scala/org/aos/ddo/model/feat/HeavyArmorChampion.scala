package org.aos.ddo.model.feat

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfClass}

/** Icon Heavy Armor Training.png
  * Heavy Armor Champion 	Passive 	While in heavy armor, get +12 PRR and MRR.
  * *
  * Level 14: Fighter
  * *
  * Note: they all stack with each other.
  * *
  */
trait HeavyArmorChampion extends FeatRequisiteImpl with Passive with RequiresAllOfClass {
  self: Feat =>
  override def allOfClass: Seq[(CharacterClass, Int)] = List((CharacterClass.Fighter, 14))
}
