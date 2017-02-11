package org.aos.ddo.model.feat

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfClass}

/** Icon Tactical Training.png
  * Tactical Mastery 	Passive 	+6 bonus to Tactics DC's.
  * *
  * Level 12: Fighter
  * */
trait TacticalMastery extends FeatRequisiteImpl with Passive with RequiresAllOfClass {
  self: Feat =>
  override def allOfClass: Seq[(CharacterClass, Int)] = List((CharacterClass.Fighter, 12))
}
