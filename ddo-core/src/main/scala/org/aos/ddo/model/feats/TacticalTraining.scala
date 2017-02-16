package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfClass}

/** Icon 	Feat 	Type 	Description 	Prerequisites
  * Icon Tactical Training.png
  * Tactical Training 	Passive 	+2 bonus to Tactics DC's.
  * *
  * Level 4: Fighter
  * */
trait TacticalTraining extends FeatRequisiteImpl with Passive with RequiresAllOfClass {
  self: GeneralFeat =>
  override def allOfClass: Seq[(CharacterClass, Int)] = List((CharacterClass.Fighter, 4))
}
