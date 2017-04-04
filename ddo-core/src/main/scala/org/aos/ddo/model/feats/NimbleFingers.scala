package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfClass
}

/** Icon Feat Nimble Fingers.png
  * Nimble Fingers
  * Passive
  * Provides a +2 bonus to the character's Disable Device and Open Lock skills.
  * *
  * Level 1: Rogue
  * */
protected[feats] trait NimbleFingers
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with RequiresAllOfClass { self: GeneralFeat =>
  override def allOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Rogue, 1))
}
