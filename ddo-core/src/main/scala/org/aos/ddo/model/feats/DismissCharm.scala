package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  * Activate this short-ranged ability while targeting a charmed, commanded, controlled,
  * or dominated enemy that is under your control to dispel the controlling effect.
  *
  * @note As of Update 26 all classes now receive this feat at level 1
  */
protected[feats] trait DismissCharm
    extends FeatRequisiteImpl
    with Active
    with FreeFeat
    with ClassRequisiteImpl
    with GrantsToClass { self: GeneralFeat =>

  override def grantToClass: Seq[(CharacterClass, Int)] =
    CharacterClass.values.map((_, 1))
}
