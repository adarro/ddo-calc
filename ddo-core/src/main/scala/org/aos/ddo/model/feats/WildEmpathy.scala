package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.{Druid, Ranger}
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAnyOfClass}

/**
  *Activate this ability to lower an animal's or beast's aggression, effectively mesmerizing them.
  *
  * Target: Enemy animals and magical beasts
  * Duration: 5 Min
  * Save: Will
  */
protected[feats] trait WildEmpathy
    extends FeatRequisiteImpl
    with Active
    with GrantsToClass
    with RequiresAnyOfClass {
  override def anyOfClass: Seq[(CharacterClass, Int)] =
    List((Druid, 1), (Ranger, 1))

  override def grantToClass: Seq[(CharacterClass, Int)] =
    List((Druid, 1), (Ranger, 1))
}
