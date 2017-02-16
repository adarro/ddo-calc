package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfClass}

/** Icon Heavy Armor Training.png
  * Heavy Armor Combatant 	Passive 	While in heavy armor, get +6 PRR and MRR.
  * *
  * Level 6: Fighter
  * */
trait HeavyArmorCombatant extends FeatRequisiteImpl with Passive with RequiresAllOfClass {
  self: GeneralFeat =>
  override def allOfClass: Seq[(CharacterClass, Int)] = List((CharacterClass.Fighter, 6))
}
