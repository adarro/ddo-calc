package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Monk
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
  * You have perfected the ability to recover from a fall unharmed.
  * You never take damage from falling no matter how far you fall.
  */
trait PerfectSlowFall
    extends FeatRequisiteImpl
    with Active
    with GrantsToClass
    with RequiresAllOfClass {
  override def grantToClass: Seq[(CharacterClass, Int)] =
    List((Monk, 20))

  override def allOfClass: Seq[(CharacterClass, Int)] =
    List((Monk, 20))
}
