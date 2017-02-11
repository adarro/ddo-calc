package org.aos.ddo.model.feat

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfClass}

/** Icon Feat Nimble Fingers.png
  * Nimble Fingers 	Passive 	Provides a +2 bonus to the character's Disable Device and Open Lock skills.
  * *
  * Level 1: Rogue
  * */
protected[feat] trait NimbleFingers extends FeatRequisiteImpl with Passive with RequiresAllOfClass {
  self: Feat =>
  override def allOfClass: Seq[(CharacterClass, Int)] = List((CharacterClass.Rogue, 1))
}
