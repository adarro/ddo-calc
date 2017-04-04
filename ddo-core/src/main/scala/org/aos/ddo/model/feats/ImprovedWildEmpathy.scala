package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.{Druid, Ranger}
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAnyOfClass
}

/**
  * This feat allows a ranger to charm an animal (similar to the Charm monster spell) for 5 minutes.
  * It can be used 3 times / rest period with a 60 second cool down.
  *
  * Target: Animal, Magical Beast
  * Save: Will
  */
protected[feats] trait ImprovedWildEmpathy
    extends FeatRequisiteImpl
    with Active
    with GrantsToClass
    with RequiresAnyOfClass {
  override def anyOfClass: Seq[(CharacterClass, Int)] =
    List((Druid, 7), (Ranger, 7))

  override def grantToClass: Seq[(CharacterClass, Int)] =
    List((Druid, 7), (Ranger, 7))
}
