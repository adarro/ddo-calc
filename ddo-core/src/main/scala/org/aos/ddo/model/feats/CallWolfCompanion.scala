package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Druid
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
  * Allows a druid to call her wolf companion.
  */
protected[feats] trait CallWolfCompanion
    extends FeatRequisiteImpl
    with Active
    with GrantsToClass
    with RequiresAllOfClass {
  override def allOfClass: Seq[(CharacterClass, Int)] = List((Druid, 1))

  override def grantToClass: Seq[(CharacterClass, Int)] =List((Druid, 1))
}
