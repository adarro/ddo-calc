package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Artificer
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
  * Created by adarr on 2/16/2017.
  */
protected[feats] trait RetainEssence
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with RequiresAllOfClass {
  override def allOfClass: Seq[(CharacterClass, Int)] = List((Artificer, 5))
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Artificer, 5))
}
