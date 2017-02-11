package org.aos.ddo.model.feat

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfClass}

/** Icon Tactical Training.png
  * Tactical Combatant 	Passive 	+4 bonus to Tactics DC's.
  * *
  * Level 8: Fighter
  * */
trait TacticalCombatant extends FeatRequisiteImpl with Passive with RequiresAllOfClass {
  self: Feat =>
  override def allOfClass: Seq[(CharacterClass, Int)] = List((CharacterClass.Fighter, 8))
}
