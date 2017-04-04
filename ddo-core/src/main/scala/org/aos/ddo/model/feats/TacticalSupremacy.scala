package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfClass
}

/** Icon Tactical Training.png
  * Tactical Supremacy
  * Passive
  * +8 bonus to Tactics DC's.
  *
  * Level 16: Fighter
  *
  * Note: they all stack with each other.
  * */
trait TacticalSupremacy
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with RequiresAllOfClass { self: GeneralFeat =>
  override def allOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Fighter, 16))
}
