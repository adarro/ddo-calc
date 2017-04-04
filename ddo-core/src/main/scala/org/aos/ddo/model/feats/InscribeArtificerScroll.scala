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
trait InscribeArtificerScroll
    extends FeatRequisiteImpl
    with Active
    with RequiresAllOfClass
    with GrantsToClass {
  override def allOfClass: Seq[(CharacterClass, Int)] = List((Artificer, 1))
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Artificer, 1))
}
