package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Ranger
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  * Icon Feat Diehard.png
  * Diehard
  * Passive
  * You automatically stabilize when incapacitated.
  */
protected[feats] trait Diehard
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with FreeFeat
    with GrantsToClass { self: GeneralFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Ranger, 3))
}
