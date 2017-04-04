package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfClass
}

/** Icon Tactical Training.png
  * Tactical Mastery
  * Passive
  * +6 bonus to Tactics DC's.
  * Level 12: Fighter
  * */
trait TacticalMastery
    extends FeatRequisiteImpl
    with Passive
    with ClassRequisiteImpl
    with RequiresAllOfClass { self: GeneralFeat =>
  override def allOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Fighter, 12))
}
