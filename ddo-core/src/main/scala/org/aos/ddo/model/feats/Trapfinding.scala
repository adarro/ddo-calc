package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.{Artificer, Rogue}
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAnyOfClass
}

/**
  * Created by adarr on 2/16/2017.
  */
protected[feats] trait Trapfinding
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with RequiresAnyOfClass {
  override def anyOfClass: Seq[(CharacterClass, Int)] =
    List((Rogue, 1), (Artificer, 1))

  override def grantToClass: Seq[(CharacterClass, Int)] =
    List((Rogue, 1), (Artificer, 1))
}
