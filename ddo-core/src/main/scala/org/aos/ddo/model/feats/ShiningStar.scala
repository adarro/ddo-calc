package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Monk
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
  * You use the combined power of Earth, Wind, and Fire to set up harmonic vibrations within your enemy that force them to dance.
  * A successful Will save negates this effect. (DC 10 + Monk Level + Charisma mod
  */
trait ShiningStar
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with RequiresAllOfClass {
  override def grantToClass: Seq[(CharacterClass, Int)] =
    List((Monk, 20))

  override def allOfClass: Seq[(CharacterClass, Int)] =
    List((Monk, 20))
}
