package org.aos.ddo.model.feats


import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Druid
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
  * You gain an additional spell preparation slot per spell level to cast Summon Nature's Ally spell of that level.
  */
protected[feats] trait DruidicOath
  extends FeatRequisiteImpl
    with Active
    with GrantsToClass
    with RequiresAllOfClass  {
  override def allOfClass: Seq[(CharacterClass, Int)] = List((Druid, 1))

  override def grantToClass: Seq[(CharacterClass, Int)] =List((Druid, 1))


}

